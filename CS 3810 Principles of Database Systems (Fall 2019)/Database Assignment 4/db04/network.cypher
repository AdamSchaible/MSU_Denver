// Query 1. Main query to Add 411 labels, Create 411 nodes, Set 822 properties, Create 1153 relationships:
CALL apoc.load.json('file:///network.json') YIELD value AS network
MERGE ( a:code { code: network.a.id, name: network.a.name } )
MERGE ( b:code { code: network.b.id, name: network.b.name } )
MERGE (a)-[:knows]->(b);


// Query 2. To generate id, community for export of nodes.json:
CALL algo.louvain.stream(null, 'knows')
YIELD nodeId, community
RETURN algo.asNode(nodeId).code as id, community
ORDER BY community;
