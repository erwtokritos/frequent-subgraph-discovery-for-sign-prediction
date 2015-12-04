/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.objects;


/**
 *
 * @author thanos
 */
public class GraphletType {

    public static int compute(Matrix matrix) {

        if (matrix.getRowDimension() == 2) {
            if (matrix.getValue(0, 1) == 1 && matrix.getValue(1, 0) == 1) {  //[0 1 , 1 0] case
                return 0;
            } else if (matrix.getValue(0, 1) == 0 && matrix.getValue(1, 0) == 1) { //[0 0 , 1 0] case
                return 1;
            } else if (matrix.getValue(0, 1) == 1 && matrix.getValue(1, 0) == -1) { //[0 1 , -1 0] case
                return 2;
            } else if (matrix.getValue(0, 1) == 0 && matrix.getValue(1, 0) == -1) { //[0 0 , -1 0] case
                return 3;
            } else {  //[0 -1 , -1 0] case
                return 4;
            }
        } else if (matrix.getRowDimension() == 3) {

            if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {-1, 0, -1}, {-1, -1, 0}})) {
                return 0;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 1;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, -1}, {-1, -1, 0}})) {
                return 2;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, -1}, {-1, -1, 0}})) {
                return 3;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, -1}, {-1, -1, 0}})) {
                return 4;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, -1}, {-1, -1, 0}})) {
                return 5;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, -1}, {-1, -1, 0}})) {
                return 6;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 7;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 8;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, -1}, {-1, 0, 0}})) {
                return 9;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {-1, 0, 0}, {0, 0, 0}})) {
                return 10;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {1, 0, 0}, {-1, -1, 0}})) {
                return 11;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {-1, 0, 0}, {1, -1, 0}})) {
                return 12;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {1, 0, 0}, {-1, 0, 0}})) {
                return 13;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {-1, 0, 0}, {1, 1, 0}})) {
                return 14;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, -1}, {-1, -1, 0}})) {
                return 15;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 16;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, -1}, {-1, -1, 0}})) {
                return 17;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, 0}, {-1, -1, 0}})) {
                return 18;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 1}, {-1, -1, 0}})) {
                return 19;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, -1}, {1, -1, 0}})) {
                return 20;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, -1}, {-1, -1, 0}})) {
                return 21;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, -1}, {-1, -1, 0}})) {
                return 22;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 23;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 24;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 25;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 26;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, -1}, {-1, 0, 0}})) {
                return 27;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 28;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {0, 0, 0}, {-1, 0, 0}})) {
                return 29;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {0, 0, 0}, {-1, -1, 0}})) {
                return 30;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {-1, -1, 0}})) {
                return 31;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 1}, {-1, -1, 0}})) {
                return 32;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, -1}, {0, 0, 0}})) {
                return 33;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 1}, {-1, -1, 0}})) {
                return 34;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {-1, -1, 0}})) {
                return 35;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {-1, -1, 0}})) {
                return 36;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {-1, -1, 0}})) {
                return 37;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {1, 0, 0}, {-1, -1, 0}})) {
                return 38;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {-1, -1, 0}})) {
                return 39;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, -1}, {-1, -1, 0}})) {
                return 40;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, 0}, {-1, -1, 0}})) {
                return 41;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 1}, {-1, -1, 0}})) {
                return 42;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, -1}, {-1, 1, 0}})) {
                return 43;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 1}, {-1, -1, 0}})) {
                return 44;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {1, 0, 0}, {1, 0, 0}})) {
                return 45;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, -1}, {1, 1, 0}})) {
                return 46;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 1}, {-1, -1, 0}})) {
                return 47;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, -1}, {-1, 1, 0}})) {
                return 48;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, -1}, {0, 0, 0}})) {
                return 49;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, -1}, {1, -1, 0}})) {
                return 50;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, -1}, {1, 1, 0}})) {
                return 51;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 52;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 53;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {1, 0, 0}})) {
                return 54;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {1, -1, 0}})) {
                return 55;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {1, 1, 0}})) {
                return 56;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 1}, {-1, 0, 0}})) {
                return 57;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {1, -1, 0}})) {
                return 58;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 59;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {0, 0, 0}})) {
                return 60;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, 0}, {-1, 0, 0}})) {
                return 61;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 62;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 1}, {1, 0, 0}})) {
                return 63;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {1, 1, 0}})) {
                return 64;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 65;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {0, 0, -1}, {-1, 0, 0}})) {
                return 66;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {1, 0, -1}, {-1, 0, 0}})) {
                return 67;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {0, 0, 0}, {-1, 0, 0}})) {
                return 68;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {1, 0, 0}, {-1, 0, 0}})) {
                return 69;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {0, 0, -1}, {-1, 0, 0}})) {
                return 70;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {1, 0, 1}, {-1, 0, 0}})) {
                return 71;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {1, -1, 0}})) {
                return 72;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 73;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {-1, 0, 0}})) {
                return 74;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 75;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {0, 0, 0}, {1, 0, 0}})) {
                return 76;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {1, 1, 0}})) {
                return 77;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 78;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {-1, 0, 0}, {0, -1, 0}})) {
                return 79;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {-1, 0, 0}})) {
                return 80;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 81;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {0, 0, 0}, {-1, 0, 0}})) {
                return 82;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {-1, 0, 0}})) {
                return 83;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {-1, 0, 0}})) {
                return 84;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {0, 0, 1}, {-1, 0, 0}})) {
                return 85;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 1}, {-1, 0, 0}})) {
                return 86;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {1, -1, 0}})) {
                return 87;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 88;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {0, 0, 0}})) {
                return 89;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, 0}, {-1, 0, 0}})) {
                return 90;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 91;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {1, 0, -1}, {1, 0, 0}})) {
                return 92;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {1, 1, 0}})) {
                return 93;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {1, -1, 0}})) {
                return 94;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {1, 0, 0}})) {
                return 95;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 96;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {1, 0, 0}, {1, 0, 0}})) {
                return 97;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {1, 1, 0}})) {
                return 98;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {1, -1, 0}})) {
                return 99;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 100;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {0, 0, 0}})) {
                return 101;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {-1, 0, 0}})) {
                return 102;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 103;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {1, 0, 1}, {1, 0, 0}})) {
                return 104;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {1, 1, 0}})) {
                return 105;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 1}, {-1, 1, 0}})) {
                return 106;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, -1}, {1, -1, 0}})) {
                return 107;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, 0}, {-1, 1, 0}})) {
                return 108;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 1}, {1, -1, 0}})) {
                return 109;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, -1}, {1, -1, 0}})) {
                return 110;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {0, 0, 0}, {-1, 0, 0}})) {
                return 111;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 1}, {1, -1, 0}})) {
                return 112;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {0, 0, 0}, {-1, 1, 0}})) {
                return 113;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {-1, 1, 0}})) {
                return 114;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 1}, {-1, 1, 0}})) {
                return 115;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, 0}, {0, 0, 0}})) {
                return 116;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 1}, {-1, 1, 0}})) {
                return 117;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {-1, 1, 0}})) {
                return 118;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {1, -1, 0}})) {
                return 119;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {-1, 1, 0}})) {
                return 120;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {-1, 1, 0}})) {
                return 121;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {1, 0, 0}})) {
                return 122;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {1, -1, 0}})) {
                return 123;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, -1}, {-1, 1, 0}})) {
                return 124;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, -1}, {-1, 1, 0}})) {
                return 125;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, 0}, {-1, 1, 0}})) {
                return 126;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, -1}, {1, -1, 0}})) {
                return 127;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, 0}, {1, -1, 0}})) {
                return 128;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, -1}, {-1, 1, 0}})) {
                return 129;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, 0}, {0, 0, 0}})) {
                return 130;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {1, 0, 0}})) {
                return 131;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, 0}, {1, -1, 0}})) {
                return 132;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, 0}, {1, 0, 0}})) {
                return 133;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, 0}, {1, 1, 0}})) {
                return 134;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 1}, {1, -1, 0}})) {
                return 135;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 1}, {1, -1, 0}})) {
                return 136;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {-1, 1, 0}})) {
                return 137;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 1}, {-1, 1, 0}})) {
                return 138;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, 0}, {1, 1, 0}})) {
                return 139;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 1}, {-1, 1, 0}})) {
                return 140;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {-1, 0, 0}, {0, -1, 0}})) {
                return 141;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {0, 0, 0}, {-1, -1, 0}})) {
                return 142;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {0, -1, 0}})) {
                return 143;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {1, 0, 0}, {-1, 1, 0}})) {
                return 144;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {1, 0, 0}, {0, -1, 0}})) {
                return 145;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {1, 0, 0}, {1, -1, 0}})) {
                return 146;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, -1}, {0, -1, 0}})) {
                return 147;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {0, 0, 0}, {-1, -1, 0}})) {
                return 148;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {0, 1, 0}})) {
                return 149;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {0, 0, -1}, {-1, -1, 0}})) {
                return 150;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {0, 0, 0}, {-1, 0, 0}})) {
                return 151;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {0, 0, 0}, {-1, 0, 0}})) {
                return 152;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {0, 0, 0}, {1, -1, 0}})) {
                return 153;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {0, 0, 0}, {1, 1, 0}})) {
                return 154;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {0, 0, 0}})) {
                return 155;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {0, 0, 0}})) {
                return 156;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {0, 0, 0}, {1, 0, 0}})) {
                return 157;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {0, 0, 0}})) {
                return 158;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {1, 0, 0}})) {
                return 159;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {1, 1, 0}})) {
                return 160;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 1}, {1, 1, 0}})) {
                return 161;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {1, 0, 0}, {1, -1, 0}})) {
                return 162;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {1, 0, 0}})) {
                return 163;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, 0}, {1, 0, 0}})) {
                return 164;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {1, 0, 0}})) {
                return 165;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {1, -1, 0}})) {
                return 166;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {1, 0, 0}, {-1, 1, 0}})) {
                return 167;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {1, -1, 0}})) {
                return 168;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {1, -1, 0}})) {
                return 169;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {1, 0, 0}, {0, 1, 0}})) {
                return 170;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 1}, {1, 0, 0}})) {
                return 171;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {1, 1, 0}})) {
                return 172;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {1, 1, 0}})) {
                return 173;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {1, 1, 0}})) {
                return 174;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {1, 1, 0}})) {
                return 175;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 1}, {-1, 1, 0}})) {
                return 176;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 1}, {1, -1, 0}})) {
                return 177;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, -1}, {-1, 1, 0}})) {
                return 178;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, -1}, {1, -1, 0}})) {
                return 179;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 1}, {1, -1, 0}})) {
                return 180;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 0, 0}})) {
                return 181;
            } else if(eq(matrix.getData(), new int[][]{{0,1,1},{1,0,1},{1,1,0}}))  {
                return 182;
            } else {
            return -1;
        
            }
        } else {
            return -1;
        }
    }

    public static int computeGraphletCode(Matrix matrix) {

        if (matrix.getRowDimension() == 3) {

            if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, -1}, {-1, -1, 0}})) {
                return 0;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 1;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 2;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 3;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 4;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 5;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 6;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 7;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {-1, -1, 0}})) {
                return 8;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 9;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 1}, {-1, 0, 0}})) {
                return 10;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {-1, 0, 0}, {0, -1, 0}})) {
                return 11;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {0, 0, 0}, {-1, 0, 0}})) {
                return 12;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {-1, 0, 0}, {0, -1, 0}})) {
                return 13;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {1, 0, -1}, {-1, 0, 0}})) {
                return 14;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {1, 0, 0}, {-1, 0, 0}})) {
                return 15;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {1, 0, 1}, {-1, 0, 0}})) {
                return 16;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, -1}, {-1, 1, 0}})) {
                return 17;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 18;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {1, -1, 0}})) {
                return 19;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {1, -1, 0}})) {
                return 20;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {-1, 0, 0}})) {
                return 21;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {1, -1, 0}})) {
                return 22;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {1, -1, 0}})) {
                return 23;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {1, -1, 0}})) {
                return 24;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 25;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 26;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 27;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 28;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 29;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {-1, 0, 0}})) {
                return 30;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {0, 0, 0}, {-1, -1, 0}})) {
                return 31;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {0, 0, 0}})) {
                return 32;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {0, -1, 0}})) {
                return 33;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {0, 0, 0}, {-1, 0, 0}})) {
                return 34;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {0, 0, 0}})) {
                return 35;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {0, 0, 0}})) {
                return 36;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {1, 0, 0}, {-1, 0, 0}})) {
                return 37;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {1, 0, 0}})) {
                return 38;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, 0}, {-1, 0, 0}})) {
                return 39;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {-1, 0, 0}})) {
                return 40;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {-1, 0, 0}})) {
                return 41;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, 0}, {-1, 0, 0}})) {
                return 42;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {1, 0, 0}})) {
                return 43;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {-1, 0, 0}})) {
                return 44;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, -1}, {1, -1, 0}})) {
                return 45;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {1, -1, 0}})) {
                return 46;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 47;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 48;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {-1, 0, 0}})) {
                return 49;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 50;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 51;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {-1, 1, 0}})) {
                return 52;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {1, 0, 0}, {-1, -1, 0}})) {
                return 53;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {1, 0, 0}, {-1, 1, 0}})) {
                return 54;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {0, 0, -1}, {-1, 0, 0}})) {
                return 55;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {0, 0, 0}, {1, 0, 0}})) {
                return 56;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {1, 0, 0}, {0, -1, 0}})) {
                return 57;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, -1}, {1, 0, 0}, {1, -1, 0}})) {
                return 58;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {1, 0, 0}, {1, 0, 0}})) {
                return 59;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {1, 0, 1}, {1, 0, 0}})) {
                return 60;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, -1}, {1, 1, 0}})) {
                return 61;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 0}, {-1, 0, 0}, {1, 1, 0}})) {
                return 62;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {-1, 0, 0}, {1, 1, 0}})) {
                return 63;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {1, 1, 0}})) {
                return 64;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 1}, {-1, 0, 0}})) {
                return 65;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {-1, 0, 0}, {1, 1, 0}})) {
                return 66;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 0}, {1, 1, 0}})) {
                return 67;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {-1, 0, 0}, {1, 1, 0}})) {
                return 68;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, -1}, {-1, -1, 0}})) {
                return 69;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 1}, {-1, -1, 0}})) {
                return 70;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, -1}, {0, -1, 0}})) {
                return 71;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {0, 0, 0}, {-1, -1, 0}})) {
                return 72;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {0, 0, 0}, {-1, -1, 0}})) {
                return 73;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, -1}, {-1, -1, 0}})) {
                return 74;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {-1, -1, 0}})) {
                return 75;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 1}, {-1, -1, 0}})) {
                return 76;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 0}, {0, 1, 0}})) {
                return 77;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 1}, {-1, 1, 0}})) {
                return 78;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {0, 0, 0}, {-1, 0, 0}})) {
                return 79;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {0, 0, 0}, {-1, 1, 0}})) {
                return 80;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, 0}, {0, 0, 0}})) {
                return 81;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {-1, 0, 1}, {1, -1, 0}})) {
                return 82;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {-1, 1, 0}})) {
                return 83;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 1}, {-1, 1, 0}})) {
                return 84;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {0, 0, -1}, {-1, -1, 0}})) {
                return 85;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {0, 0, 0}, {-1, 0, 0}})) {
                return 86;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {0, 0, 0}, {-1, 0, 0}})) {
                return 87;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {0, 0, 0}, {1, -1, 0}})) {
                return 88;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {0, 0, 0}, {1, 1, 0}})) {
                return 89;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {0, 0, 0}})) {
                return 90;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {0, 0, 0}})) {
                return 91;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {0, 0, 0}, {1, 0, 0}})) {
                return 92;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {0, 0, 0}})) {
                return 93;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, -1}, {-1, 1, 0}})) {
                return 94;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, 0}, {0, 0, 0}})) {
                return 95;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, -1}, {1, -1, 0}})) {
                return 96;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {1, -1, 0}})) {
                return 97;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 1}, {1, -1, 0}})) {
                return 98;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {1, 0, 0}})) {
                return 99;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 0}, {1, 1, 0}})) {
                return 100;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 0}, {1, 0, 1}, {1, 1, 0}})) {
                return 101;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, -1}, {-1, -1, 0}})) {
                return 102;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {-1, -1, 0}})) {
                return 103;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {1, 0, 0}, {-1, -1, 0}})) {
                return 104;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {-1, -1, 0}})) {
                return 105;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {-1, -1, 0}})) {
                return 106;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {-1, -1, 0}})) {
                return 107;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {0, 0, 1}, {-1, 0, 0}})) {
                return 108;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, -1}, {-1, 1, 0}})) {
                return 109;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {-1, 1, 0}})) {
                return 110;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {1, 0, 0}, {1, -1, 0}})) {
                return 111;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {-1, 1, 0}})) {
                return 112;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {-1, 1, 0}})) {
                return 113;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {1, -1, 0}})) {
                return 114;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, -1}, {1, 0, 0}, {1, 0, 0}})) {
                return 115;
            } else if (eq(matrix.getData(), new int[][]{{0, -1, 1}, {1, 0, 0}, {1, 0, 0}})) {
                return 116;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {1, 0, 0}})) {
                return 117;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, -1}, {1, 0, 0}, {1, 0, 0}})) {
                return 118;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {1, 0, 0}})) {
                return 119;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, -1}, {1, -1, 0}})) {
                return 120;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {1, -1, 0}})) {
                return 121;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {1, 0, 0}, {-1, 1, 0}})) {
                return 122;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {1, -1, 0}})) {
                return 123;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {1, -1, 0}})) {
                return 124;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {-1, 1, 0}})) {
                return 125;
            } else if (eq(matrix.getData(), new int[][]{{0, 0, 1}, {1, 0, 0}, {0, 1, 0}})) {
                return 126;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 1}, {1, 0, 0}})) {
                return 127;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, -1}, {1, 1, 0}})) {
                return 128;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {-1, 0, 1}, {1, 1, 0}})) {
                return 129;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, -1}, {1, 1, 0}})) {
                return 130;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 0}, {1, 0, 0}, {1, 1, 0}})) {
                return 131;
            } else if (eq(matrix.getData(), new int[][]{{0, 1, 1}, {1, 0, 0}, {1, 1, 0}})) {
                return 132;
            }
        } 
        
        return -1;
    }
    
    private static boolean eq(int[][] matrix1, int[][] matrix2) {

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {

                if (matrix1[i][j] != matrix2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int getMaxGraphletNumber(int k) {

        if (k == 2) {
            return 5;
        } else if (k == 3) {
            return 133;
        } else {
            return -1;
        }
    }
}
