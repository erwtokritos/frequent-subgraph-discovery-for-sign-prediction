/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import test.Combination;
import test.Permutation;

final public class Matrix {

    private int M;             // number of rows
    private int N;             // number of columns
    private int[][] data;   // M-by-N array

    public int[][] getData() {
        return data;
    }
    private int[][] transpose;   // M-by-N array
    private int lastNotZeroColumn, lastNonZeroRow;

    public int getLastNotZeroColumn() {
        return lastNotZeroColumn;
    }

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new int[M][N];
        transpose = new int[N][M];
        lastNotZeroColumn = N;
        lastNonZeroRow = M;
    }

    // create matrix based on 2d array
    public Matrix(int[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new int[M][N];
        this.transpose = new int[N][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                this.data[i][j] = data[i][j];
                this.transpose[j][i] = data[i][j];
            }
        }

        lastNotZeroColumn = N;
        lastNonZeroRow = M;
    }

    // copy constructor
    private Matrix(Matrix A) {
        this(A.data);
    }

    /**
     * Returns the subgraph with the specified vertices
     *
     * @param vertices
     * @return
     */
    public Matrix subgraph(int[] vertices) {

        int k = vertices.length;
        Matrix sub = new Matrix(k, k);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                sub.setValue(i, j, this.data[vertices[i]][vertices[j]]);
            }
        }

        return sub;
    }

    public List<Matrix> enumerateSubgraphs(int size) {

        List<Matrix> res = new ArrayList<Matrix>();

        Combination comb = new Combination(this.data.length, size);
        while (comb.hasNext()) {
            int[] vs = comb.next();
            Matrix sub = subgraph(vs);
            res.add(sub);
        }

        return res;
    }

    /**
     * Returns the kxk submatrix having the (i,j) element in the top left corner
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param k The size of the window
     * @return
     */
    public Matrix submatrix(int x, int y, int k) {

        Matrix sub = new Matrix(k, k);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                sub.setValue(i, j, this.data[x + i][y + j]);
            }
        }

        return sub;
    }

    public int[] getRow(int i) {
        if (i < 0 || i >= M) {
            return null;
        }

        return data[i];
    }

    public void setValue(int i, int j, int value) {
        this.data[i][j] = value;
        this.transpose[j][i] = value;
    }

    public int getValue(int i, int j) {

        return this.data[i][j];
    }

    public void setRow(int row, int[] values) {

        data[row] = values;
        for (int i = 0; i < getColumnDimension(); i++) {
            transpose[i][row] = values[i];
        }
    }

    public void setCol(int col, int[] values) {

        for (int i = 0; i < getRowDimension(); i++) {
            data[i][col] = values[i];
        }

        transpose[col] = values;
    }

    public int getRowDimension() {
        return M;
    }

    public int getColumnDimension() {
        return N;
    }

    public int[] getCol(int j) {
        if (j < 0 || j >= N) {
            return null;
        }

        return transpose[j];
    }

    /**
     * Sorts vertices by their in-degree of positive votes
     *
     * @param vertices
     * @return
     */
    private List<Integer> sortFurther(List<Integer> vertices) {

        List<Integer> orderedList = new ArrayList<Integer>();

        Matrix m = new Matrix(vertices.size(), vertices.size());

        return orderedList;
    }

    private List<Integer> resolveOrder(List<Integer> vertices) {

        List<Integer> orderedList = new ArrayList<Integer>();
        List<Integer> counts = new ArrayList<Integer>();
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>(getRowDimension());
        for (int i = 0; i < vertices.size(); i++) {

            int row_id = vertices.get(i);
            int ones = Graphlets.countChar(getRow(row_id), 1);
            if (!counts.contains(ones)) {
                counts.add(ones);
            }

            List<Integer> get = map.get(ones);
            if (get == null) {
                get = new ArrayList<Integer>();
            }
            get.add(row_id);
            map.put(ones, get);
        }

        Collections.sort(counts);
        int size = counts.size();

        for (int count = 0; count < counts.size(); count++) {

            List<Integer> mylist = map.get(counts.get(size - 1 - count));
            //if there is just one column just add it to the matrix
            if (mylist.size() == 1) {
                orderedList.add(mylist.get(0));
            } else {
                for (int id : mylist) {
                    orderedList.add(id);
                }
            }
        }
        return orderedList;
    }

    /**
     * Vectorize the adjacency matrix
     */
    public int[] vectorize() {

        int[] response = new int[getRowDimension() * getColumnDimension()];
        for (int i = 0; i < getColumnDimension(); i++) {

            int[] col = getCol(i);
            for (int j = 0; j < col.length; j++) {
                response[i * getColumnDimension() + j] = col[j];
            }

        }

        return response;
    }

    /**
     * Vectorize the adjacency matrix
     */
    public int[] vectorize(Matrix m) {

        int[] response = new int[m.getRowDimension() * m.getColumnDimension()];
        for (int i = 0; i < m.getColumnDimension(); i++) {

            int[] col = m.getCol(i);
            for (int j = 0; j < col.length; j++) {
                response[i * m.getColumnDimension() + j] = col[j];
            }

        }

        return response;
    }

    /**
     * First it organizes the matrix by putting the columns with most non-zero
     * elements to the left. In case of equalities, it reorganizes the matrix by
     * putting the rows with most ones on the top
     */
    public String getCanonicalLabel() {

        String code = "";
        List<Integer> counts = new ArrayList<Integer>();
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>(getRowDimension());

        //sort vertices by total degree
        for (int j = 0; j < getColumnDimension(); j++) {

            int ones = Graphlets.countChar(getCol(j), 1);
            int minus = Graphlets.countChar(getCol(j), -1);
            int total = ones + minus;

            if (!counts.contains(total)) {
                counts.add(total);
            }

            List<Integer> get = map.get(total);
            if (get == null) {
                get = new ArrayList<Integer>();
            }
            get.add(j);
            map.put(total, get);
        }

        Comparator<Integer> comparator = Collections.reverseOrder();
        Collections.sort(counts, comparator);

        int[][] c = new int[getColumnDimension()][getRowDimension()];
        int[][] r = new int[getRowDimension()][getColumnDimension()];

        int counter = 0;
        int size = counts.size();
        for (int count = 0; count < counts.size(); count++) {

            List<Integer> mylist = map.get(counts.get(size - 1 - count));
            //if there is just one column just add it to the matrix
            if (mylist.size() == 1) {
                int column_id = mylist.get(0);

                c[counter] = getCol(column_id);
                r[counter] = getRow(column_id);
                counter++;
            } else {

                List<Integer> orderedList = sortFurther(mylist);
                for (int id : orderedList) {

                    c[counter] = getCol(id);
                    r[counter] = getRow(id);
                    counter++;
                }
            }

        }

        for (int i = 0; i < getRowDimension(); i++) {
            setRow(i, r[i]);
        }
        for (int i = 0; i < getColumnDimension(); i++) {
            setCol(i, c[i]);
        }

        return code;

    }

    private String printArray(int[] input) {

        String out = "";
        for (int i = 0; i < input.length; i++) {
            out += input[i];
        }
        return out;
    }

    private String printArray(int[][] input) {

        String out = "";

        for (int j = 0; j < input[0].length; j++) {
            for (int i = 0; i < input.length; i++) {
                out += input[i][j];
            }
        }
        return out;
    }

    private List<Integer> getCharPositions(String input, char ch) {
        
        List<Integer> list = new ArrayList<Integer>();

        for(int i = 0; i < input.length(); i++){
    
            if(input.charAt(i) == ch){
              list.add(i);
          }
        }
       return list;
    }
    
    private int countZeros(String input) {
        int count = 0;

        for (int i = input.length() - 1; i >= 0; i--) {

            if (input.charAt(i) != '0') {
                break;
            }
            count++;
        }

        return count;
    }
    
    private boolean isTesterBetter(List<Integer> testerOnes, List<Integer> testerMinus, 
                                   List<Integer> bestOnes, List<Integer> bestMinus) {
        
        int countOnes = testerOnes.size();
        int countMinus = testerMinus.size();
        
        if(countOnes > 0) {
            for(int i = countOnes - 1; i >= 0; i-- ) {
                
                if(testerOnes.get(i) > bestOnes.get(i))
                    return false;
                
                               
                if(testerOnes.get(i) < bestOnes.get(i))
                    return true;
            }
         }
        
        if(countMinus > 0) {
            for(int i = countMinus - 1; i >= 0; i-- ) {
                
                if(testerMinus.get(i) > bestMinus.get(i))
                    return false;
                
                               
                if(testerMinus.get(i) < bestMinus.get(i))
                    return true;
            }
         }        
        
        return false;
    }
    
    /**
     * First it organizes the matrix by putting the columns with most non-zero
     * elements to the left. In case of equalities, it reorganizes the matrix by
     * putting the rows with most ones on the top
     */
    public void standardForm() {
        
        if (getColumnDimension() == 3) {

            int[] arr = {0, 1, 2};
            Permutation p = new Permutation(arr);

            int[][] tester = new int[3][3];
            int[][] best = new int[3][3];

                    
            List<Integer> onesOfBest = new ArrayList<Integer>();
            List<Integer> minusOfBest = new ArrayList<Integer>();
            int zerosOfBest = -1;
            
            
            while (p.hasNext()) {

                int[] combination = p.getNext();

                for (int i = 0; i < combination.length; i++) {
                    for (int j = 0; j < combination.length; j++) {

                        tester[i][j] = getValue(combination[i], combination[j]);

                    }
                }
                String testcode = Graphlets.encodeGraph(new Matrix(tester));
                testcode = testcode.replace("-1", "-");
                List<Integer> ones = getCharPositions(testcode, '1');
                List<Integer> minus = getCharPositions(testcode, '-');
                int zeros = countZeros(testcode);
                
                         
                //tester is worse than best. escape
                if (zeros < zerosOfBest) {
                    continue;
                }
                     
                //found new best
                if (zeros > zerosOfBest) {
                    //System.out.println("New best is " + printArray(tester));
                    best[0][0] = tester[0][0];
                    best[0][1] = tester[0][1];
                    best[0][2] = tester[0][2];
                    best[1][0] = tester[1][0];
                    best[1][1] = tester[1][1];
                    best[1][2] = tester[1][2];
                    best[2][0] = tester[2][0];
                    best[2][1] = tester[2][1];
                    best[2][2] = tester[2][2];                    
                    zerosOfBest = zeros;
                    onesOfBest = ones;
                    minusOfBest = minus;
                }else {//compare the distribution of 1s ans -1s

                    if (isTesterBetter(ones, minus, onesOfBest, minusOfBest)) {
                        best[0][0] = tester[0][0];
                        best[0][1] = tester[0][1];
                        best[0][2] = tester[0][2];
                        best[1][0] = tester[1][0];
                        best[1][1] = tester[1][1];
                        best[1][2] = tester[1][2];
                        best[2][0] = tester[2][0];
                        best[2][1] = tester[2][1];
                        best[2][2] = tester[2][2];
                        zerosOfBest = zeros;
                        onesOfBest = ones;
                        minusOfBest = minus;
                    }
                    
                }
                
                                                         
            }

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    setValue(i, j, best[i][j]);
                }
            }
            
        } else if(getColumnDimension() == 4){
            
            
            int[] arr = {0, 1, 2, 3};
            Permutation p = new Permutation(arr);

            int[][] tester = new int[4][4];
            int[][] best = new int[4][4];

                    
            List<Integer> onesOfBest = new ArrayList<Integer>();
            List<Integer> minusOfBest = new ArrayList<Integer>();
            int zerosOfBest = -1;            
            
            while (p.hasNext()) {

                int[] combination = p.getNext();

                for (int i = 0; i < combination.length; i++) {
                    for (int j = 0; j < combination.length; j++) {

                        tester[i][j] = getValue(combination[i], combination[j]);

                    }
                }

                                
                String testcode = Graphlets.encodeGraph(new Matrix(tester));
                testcode = testcode.replace("-1", "-");
                List<Integer> ones = getCharPositions(testcode, '1');
                List<Integer> minus = getCharPositions(testcode, '-');
                int zeros = countZeros(testcode);
                
                
                if (zeros < zerosOfBest) {
                    continue;
                }

                //final check
                if (zeros > zerosOfBest) {
                    //System.out.println("New best is " + printArray(tester));
                    best[0][0] = tester[0][0];
                    best[0][1] = tester[0][1];
                    best[0][2] = tester[0][2];
                    best[0][3] = tester[0][3];
                    best[1][0] = tester[1][0];
                    best[1][1] = tester[1][1];
                    best[1][2] = tester[1][2];
                    best[1][3] = tester[1][3];
                    best[2][0] = tester[2][0];
                    best[2][1] = tester[2][1];
                    best[2][2] = tester[2][2];                    
                    best[2][3] = tester[2][3];
                    best[3][0] = tester[3][0];
                    best[3][1] = tester[3][1];
                    best[3][2] = tester[3][2];                    
                    best[3][3] = tester[3][3];             
                    
                    zerosOfBest = zeros;
                    onesOfBest = ones;
                    minusOfBest = minus;
                } else {
    
                    if (isTesterBetter(ones, minus, onesOfBest, minusOfBest)) {
                    
                        best[0][0] = tester[0][0];
                        best[0][1] = tester[0][1];
                        best[0][2] = tester[0][2];
                        best[0][3] = tester[0][3];
                        best[1][0] = tester[1][0];
                        best[1][1] = tester[1][1];
                        best[1][2] = tester[1][2];
                        best[1][3] = tester[1][3];
                        best[2][0] = tester[2][0];
                        best[2][1] = tester[2][1];
                        best[2][2] = tester[2][2];
                        best[2][3] = tester[2][3];
                        best[3][0] = tester[3][0];
                        best[3][1] = tester[3][1];
                        best[3][2] = tester[3][2];
                        best[3][3] = tester[3][3];
                        onesOfBest = ones;
                        minusOfBest = minus;
                    }                                     
            }

            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    setValue(i, j, best[i][j]);
                }
            }
            
        }
        }



        /*
         for (int j = 0; j < getColumnDimension(); j++) {
            
         NodeFreqs nodeInfo = new NodeFreqs();
            
         int column_id = j;
         nodeInfo.setNode_id(column_id);            
            
         int positiveInDegree = Graphlets.countChar(getCol(j), 1);
         int negativeInDegree = Graphlets.countChar(getCol(j), -1);
            
         int positiveOutDegree = Graphlets.countChar(getRow(j), 1);
         int negativeOutDegree = Graphlets.countChar(getRow(j), -1);
            
         //int totalDegree = positiveInDegree + negativeInDegree + positiveOutDegree + negativeOutDegree;
           
         nodeInfo.setPositiveIn(positiveInDegree);
         nodeInfo.setPositiveOut(positiveOutDegree);
         nodeInfo.setNegativeIn(negativeInDegree);
         nodeInfo.setNegativeOut(negativeOutDegree);
         }
         */

        /*
         List<Integer> counts = new ArrayList<Integer>();
         HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>(getRowDimension());

         //columns with most non-zero elements to the left in decreasing order
         for (int j = 0; j < getColumnDimension(); j++) {

         int ones = Graphlets.countChar(getCol(j), 1);
         int minus = Graphlets.countChar(getCol(j), -1);
         int total = ones + minus;

         if (!counts.contains(total)) {
         counts.add(total);
         }

         List<Integer> get = map.get(total);
         if (get == null) {
         get = new ArrayList<Integer>();
         }
         get.add(j);
         map.put(total, get);
         }

         Collections.sort(counts);

         int[][] c = new int[getColumnDimension()][getRowDimension()];
         int[][] r = new int[getRowDimension()][getColumnDimension()];

         int counter = 0;
         int size = counts.size();
         for (int count = 0; count < counts.size(); count++) {

         List<Integer> mylist = map.get(counts.get(size - 1 - count));
         //if there is just one column just add it to the matrix
         if (mylist.size() == 1) {
         int column_id = mylist.get(0);

         c[counter] = getCol(column_id);
         r[counter] = getRow(column_id);
         counter++;
         } else {
                
         List<Integer> orderedList = resolveOrder(mylist);
         for(int id : orderedList) {
                                  
         c[counter] = getCol(id);
         r[counter] = getRow(id);
         counter++;
         }
         }
                           
         }

         for (int i = 0; i < getRowDimension(); i++) {
         setRow(i, r[i]);
         }
         for (int i = 0; i < getColumnDimension(); i++) {
         setCol(i, c[i]);
         }

         */

    }

    public void reorderCols() {

        List<IntegerPair> list = new ArrayList<IntegerPair>();
        for (int j = 0; j < getColumnDimension(); j++) {

            int ones = Graphlets.countChar(getCol(j), 1);
            int minus = Graphlets.countChar(getCol(j), -1);

            IntegerPair pair = new IntegerPair();
            pair.setKey(j);
            pair.setValue(ones + minus);
            list.add(pair);
        }

        Collections.sort(list);
        int[][] m = new int[getRowDimension()][getColumnDimension()];
        int counter = 0;
        for (IntegerPair c : list) {
            int column_id = c.getKey();
            int value = c.getValue();

            m[counter] = getCol(column_id);
            counter++;
            if (value != 0) {
                lastNotZeroColumn = counter;
            }
        }

        for (int i = 0; i < getColumnDimension(); i++) {
            setCol(i, m[i]);
        }


    }

    public void reorderRows() {

        List<IntegerPair> list = new ArrayList<IntegerPair>();
        for (int i = 0; i < getRowDimension(); i++) {

            int ones = Graphlets.countChar(getRow(i), 1);
            int minus = Graphlets.countChar(getRow(i), -1);

            IntegerPair pair = new IntegerPair();
            pair.setKey(i);
            pair.setValue(ones + minus);
            list.add(pair);
        }

        Collections.sort(list);
        int[][] m = new int[getRowDimension()][getColumnDimension()];
        int counter = 0;
        for (IntegerPair c : list) {
            int row_id = c.getKey();
            int value = c.getValue();

            m[counter] = getRow(row_id);
            counter++;
            if (value != 0) {
                lastNonZeroRow = counter;
            }
        }

        for (int i = 0; i < getColumnDimension(); i++) {
            setRow(i, m[i]);
        }


    }
    /*
     // create and return a random M-by-N matrix with values between 0 and 11    
     public static Matrix random(int M, int N) {
     Matrix A = new Matrix(M, N);
     for (int i = 0; i < M; i++)
     for (int j = 0; j < N; j++)
     A.data[i][j] = Math.random();
     return A;
     }

     // create and return the N-by-N identity matrix
     public static Matrix identity(int N) {
     Matrix I = new Matrix(N, N);
     for (int i = 0; i < N; i++)
     I.data[i][i] = 1;
     return I;
     }

     // swap rows i and j
     private void swap(int i, int j) {
     double[] temp = data[i];
     data[i] = data[j];
     data[j] = temp;
     }

     // create and return the transpose of the invoking matrix
     public Matrix transpose() {
     Matrix A = new Matrix(N, M);
     for (int i = 0; i < M; i++)
     for (int j = 0; j < N; j++)
     A.data[j][i] = this.data[i][j];
     return A;
     }

     // return C = A + B
     public Matrix plus(Matrix B) {
     Matrix A = this;
     if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
     Matrix C = new Matrix(M, N);
     for (int i = 0; i < M; i++)
     for (int j = 0; j < N; j++)
     C.data[i][j] = A.data[i][j] + B.data[i][j];
     return C;
     }


     // return C = A - B
     public Matrix minus(Matrix B) {
     Matrix A = this;
     if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
     Matrix C = new Matrix(M, N);
     for (int i = 0; i < M; i++)
     for (int j = 0; j < N; j++)
     C.data[i][j] = A.data[i][j] - B.data[i][j];
     return C;
     }

     // does A = B exactly?


     // return C = A * B
     public Matrix times(Matrix B) {
     Matrix A = this;
     if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");
     Matrix C = new Matrix(A.M, B.N);
     for (int i = 0; i < C.M; i++)
     for (int j = 0; j < C.N; j++)
     for (int k = 0; k < A.N; k++)
     C.data[i][j] += (A.data[i][k] * B.data[k][j]);
     return C;
     }


     // return x = A^-1 b, assuming A is square and has full rank
     public Matrix solve(Matrix rhs) {
     if (M != N || rhs.M != N || rhs.N != 1)
     throw new RuntimeException("Illegal matrix dimensions.");

     // create copies of the data
     Matrix A = new Matrix(this);
     Matrix b = new Matrix(rhs);

     // Gaussian elimination with partial pivoting
     for (int i = 0; i < N; i++) {

     // find pivot row and swap
     int max = i;
     for (int j = i + 1; j < N; j++)
     if (Math.abs(A.data[j][i]) > Math.abs(A.data[max][i]))
     max = j;
     A.swap(i, max);
     b.swap(i, max);

     // singular
     if (A.data[i][i] == 0.0) throw new RuntimeException("Matrix is singular.");

     // pivot within b
     for (int j = i + 1; j < N; j++)
     b.data[j][0] -= b.data[i][0] * A.data[j][i] / A.data[i][i];

     // pivot within A
     for (int j = i + 1; j < N; j++) {
     double m = A.data[j][i] / A.data[i][i];
     for (int k = i+1; k < N; k++) {
     A.data[j][k] -= A.data[i][k] * m;
     }
     A.data[j][i] = 0.0;
     }
     }

     // back substitution
     Matrix x = new Matrix(N, 1);
     for (int j = N - 1; j >= 0; j--) {
     double t = 0.0;
     for (int k = j + 1; k < N; k++)
     t += A.data[j][k] * x.data[k][0];
     x.data[j][0] = (b.data[j][0] - t) / A.data[j][j];
     }
     return x;
   
     }
     */

    /**
     * Return true if the kxk matrix is weakly connected
     *
     * @return
     */
    public boolean isWeaklyConnected() {
        HashSet<IntegerPair> set = new HashSet<IntegerPair>();
        for (int i = 0; i < getRowDimension(); i++) {
            for (int j = 0; j < getColumnDimension(); j++) {
                if (this.data[i][j] != 0) {
                    IntegerPair pair = new IntegerPair();
                    if (i < j) {
                        pair.setKey(i);
                        pair.setValue(j);
                    } else {
                        pair.setKey(j);
                        pair.setValue(i);
                    }

                    set.add(pair);
                }
            }
        }

        if (set.size() >= (getRowDimension() - 1)) {
            return true;
        }

        return false;
    }

    /**
     * Return true if the kxk matrix is weakly connected
     *
     * @return
     */
    public boolean isWeaklyConnected(boolean removeSelfEdges) {
        HashSet<IntegerPair> set = new HashSet<IntegerPair>();
        for (int i = 0; i < getRowDimension(); i++) {
            for (int j = 0; j < getColumnDimension(); j++) {
                if (this.data[i][j] != 0 && (i != j)) {
                    IntegerPair pair = new IntegerPair();
                    if (i < j) {
                        pair.setKey(i);
                        pair.setValue(j);
                    } else {
                        pair.setKey(j);
                        pair.setValue(i);
                    }

                    set.add(pair);
                }
            }
        }

        if (set.size() >= (getRowDimension() - 1)) {
            return true;
        }

        return false;
    }

    /**
     * Return true if is the zero matrix
     *
     * @return
     */
    public boolean isZero() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (this.data[i][j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    // print matrix to standard output
    public void show() {
        for (int i = 0; i < getRowDimension(); i++) {
            for (int j = 0; j < getColumnDimension(); j++) {
                System.out.print(" " + data[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < this.getRowDimension(); i++) {
            for (int j = 0; j < this.getColumnDimension(); j++) {
                hash = 43 * hash * i + 53 * hash * j + this.data[i][j];

            }
        }

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matrix other = (Matrix) obj;
        if (other.getRowDimension() != this.getRowDimension()) {
            return false;
        }

        if (other.getColumnDimension() != this.getColumnDimension()) {
            return false;
        }

        for (int i = 0; i < this.getRowDimension(); i++) {
            for (int j = 0; j < this.getColumnDimension(); j++) {
                if (this.data[i][j] != other.data[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}