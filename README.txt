1.Describe the worst-case asymptotic running times of your methods adjacentVertices, edgeCost, and shortestPath. In your answers, use |E| for the number of edges and |V| for the number of vertices. Explain and justify your answers.
	The worst-case asymptotic runtime for the adjacentVerticies method is  O(|E|+|V|) since in the method had to check if Vertex was in the list. This part gives O|V|. We also had to find every edge between the two inpute 
	verticies a and b which provides us with a aysmptotic runtime of O|E|. These two procedures run seperately and therefore add up to O(|V|+|E|).
	The worst-case asysptotic runtime for the edgeCost method is similarly O(|E|+|V|) since you have to check if the verticies are in the entire list of verticies which takes O(|V|). Findind the edge between the given verticies
	gives you a aymptotic runtime of O(|E|). These two runtimes run seperates in this method giving us O(|E|+|V|). 
	The worst-case aysmptotic runtime for the shortestPath function is O(|V^2| +|V||E||). This is because within the helper function it search to find the vertex with the smallest cost which is O|V| runtime. The function also run
	through every edge to update the cost of the verticies which takes O(|E|).The whole function recurses over each vertex (of lowest cost) which in the worst case scenario is O|V|. This means that the runtime of V is multiplied by
	the runtime of (|V| +|E|) with gets you  O(|V^2| +|V||E||).
2.Describe how your code ensures the graph abstraction is protected. If your code makes extra copies of objects for (only) this purpose, explain why. If not, explain why it is not necessary.
	I ensured that graph abstraction is protected by making copies of my objects such as the vertex object specifically when they are used or returned. This is because (at least for the vertex object)
	not every thing is immutable because needed to change certain values like cost and path. These values are prone to manuliplation if someone has access the original object. This is I created new 
	copies. For the methods like verticies() and edge(), where I return field values, I makes these immutable meaning that they are final and cannot be manipulated. This means I didn't have to copy for 
	them. Both copy-in copy-out and immutibility strategies protect  the graph completely. 
3.Describe how you tested your code.
	I test my code by using the debug tool to add the given .txt files and ran it directly on eclipse. I used things like simple printlns to dettermine what was going on with certain objects a what 
	the code was actually doing in certain spots if I saw an error there. This is basic testing that we have usedin previous CS classes and it involved quite a bit trial and error on top of what 
	I was already doing. I did not use commandline debuggin tools.
4.If you worked with a partner, what did you think of the experience? At minimum, what was one thing you benefitted from or liked it, and what was one you didn't?
	I worked alone so this question doesn't apply to me. However to answer the latter part of the question. I benefitted by only having my voice in the thought process
	and working through something like dijkstra's algorithm helps with a clear focus. Unfortuneatly generate ideas and for how to tackle certain aspects like the vector 
	edge system would have defineatly been better with a partner since both of us would probably think differently.
5.If you did any above-and-beyond, describe what you did.
	I got some real-life data which represents the some of the stations in the Philadelphia Metro system. The Verticies are the stations and the cost is the time it takes 
	to get from one station to another (in minutes). Here is code for Station name and abbreviation in the .txt file: CHS - City Hall Station, 30S - 30th ST station, 69S - 69th St Station,
	NOR - Norristown Station, NOP - North Philly Station, FRA - Frankford Station, AIR - AIRPORT Station, STA - Stadium Station. 
