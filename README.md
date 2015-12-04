# frequent-subgraph-discovery-for-sign-prediction
Frequent subgraph detection for sign prediction in trust graphs

This is the code associated with my paper ["Predicting Edge Signs in Social Networks Using Frequent Subgraph Discovery"](http://www.computer.org/csdl/mags/ic/2014/05/mic2014050036-abs.html). In signed social networks, users are connected via directional signed links that indicate their opinions about each other. Predicting the signs of such links is crucial for many real-world applications, such as recommendation systems. The presented algorithm mines patterns that emerge frequently in the social graph, and it is shown that such patterns possess enough discriminative power to accurately predict the relationships among social network users. 

The algorithm is divided into 3 steps and each step is implemented by the corresponding Java class. The order is as follows. 

1st step: Execute the 'graph.functions.GraphLoader' class giving the signed social network as input. It will produce a modified version of the network which will be named something like <network-name>.network.csv

2nd step: Execute the 'graph.functions.ExploreNetwork'. It will operate on the network of the first step.  Here, you have to specify the threshold for the common neighbours threshold (default 25) and the size of the subgraph(3 or 4)

3rd step: Execute the 'graph.functions.ExploreMinSupport'. This class will identify the common subgraphs for the given minimum support ('min_support' variable) and it will produce the final output. The format of this output file is <sign>,<frequency of graph 1>,<frequency of graph 2>,... . and it should be fed to an SVM classifier for the classification task. I used [R](https://www.r-project.org/) and the [LibLinear package](https://cran.r-project.org/web/packages/LiblineaR/index.html) for this purpose.


NOTES: 

1. A sample signed network of the Wikipedia elections is given under the datasets folder. 

2. There is a dependency from the [Neo4j](http://neo4j.com/) (v1.6) graph database. The required jars are packaged under the 'deps' folder for convenience.

3. If you find this code useful I wouldn't mind if you cited my paper :P 

Athanasios Papaoikonomou, Magdalini Kardara, Konstantinos Tserpes, Theodora A. Varvarigou, "Predicting Edge Signs in Social Networks Using Frequent Subgraph Discovery", IEEE Internet Computing, vol.18, no. 5, pp. 36-43, Sept.-Oct. 2014, doi:10.1109/MIC.2014.82 
