#!/usr/bin/env python
# coding: utf-8

# In[4]:


import os
import sys
from collections import deque


# In[87]:


dict_graph = {}


# Read the data.txt file
with open('city.txt', 'r') as f:
    for l in f:
        city_a, city_b, p_cost = l.split(',')
        if city_a not in dict_graph:
            dict_graph[city_a] = {}
        dict_graph[city_a][city_b] = int(p_cost)
        if city_b not in dict_graph:
            dict_graph[city_b] = {}
        dict_graph[city_b][city_a] = int(p_cost)


# In[44]:


print(dict_graph)


# # Uniformed search algorithms :

# # - Depth First Search(DFS) algorithm.

# In[48]:


# Depth First Search Method
def DepthFirstSearch(graph, src, dst):
    stack = [(src, [src], 0)]
    visited = {src}
    while stack:
        (node, path, cost) = stack.pop()
        for temp in graph[node].keys():
            if temp == dst:
                return path + [temp], cost + graph[node][temp]
            if temp not in visited:
                visited.add(temp)
                stack.append((temp, path + [temp], cost + graph[node][temp]))


# # Breadth First Search(BFS) algorithm.

# In[47]:


# Breadth First Search Method
def BreadthFirstSearch(graph, src, dst):
    q = [(src, [src], 0)]
    visited = {src}
    while q:
        (node, path, cost) = q.pop(0)
        for temp in graph[node].keys():
            if temp == dst:
                return path + [temp], cost + graph[node][temp]
            if temp not in visited:
                visited.add(temp)
                q.append((temp, path + [temp], cost + graph[node][temp]))


# # Iterative deepening search algorithm.

# In[49]:


# Iterative Deepening Search Method
def IterativeDeepening(graph, src, dst):
    level = 0
    count = 0
    stack = [(src, [src], 0)]
    visited = {src}
    while True:
        level += 1
        while stack:
            if count <= level:
                count = 0
                (node, path, cost) = stack.pop()
                for temp in graph[node].keys():
                    if temp == dst:
                        return path + [temp], cost + graph[node][temp]
                    if temp not in visited:
                        visited.add(temp)
                        count += 1
                        stack.append((temp, path + [temp], cost + graph[node][temp]))
            else:
                q = stack
                visited_bfs = {src}
                while q:
                    (node, path, cost) = q.pop(0)
                    for temp in graph[node].keys():
                        if temp == dst:
                            return path + [temp], cost + graph[node][temp]
                        if temp not in visited_bfs:
                            visited_bfs.add(temp)
                            q.append((temp, path + [temp], cost + graph[node][temp]))
                break


# # Depth limited search algorithm.

# In[56]:


# Depth Limited Search Method
def DepthLimitedSearch(graph, src, dst,maxlenght):
    if src == dst : return True
    # If reached the maximum depth, stop recursing.
    if maxlenght <= 0 : return False
    stack = [(src, [src], 0)]
    visited = {src}
    while stack:
        (node, path, cost) = stack.pop()
        for lenght,temp in enumerate(graph[node].keys()):
            if lenght==maxlenght: break
            if temp == dst:
                return path + [temp], cost + graph[node][temp]
            if temp not in visited:
                visited.add(temp)
                stack.append((temp, path + [temp], cost + graph[node][temp]))


# In[88]:


start='Riyadh_Alkabra'
end='Buriydah'
print("#### From Riyadh_Alkabra To  Buriydah")
print ("Depth First Search: ",DepthFirstSearch(dict_graph, start, end))
print("Breadth First Search: ",BreadthFirstSearch(dict_graph, start,end))
print ("IterativeDeepening Search: ",IterativeDeepening(dict_graph, start, end))
print ("Depth Limited Search: ",DepthLimitedSearch(dict_graph, start, end,1))


# In[89]:


start='Badaiya'
end='Buriydah'
print("#### From Badaiya To  Buriydah")
print ("Depth First Search: ",DepthFirstSearch(dict_graph, start, end))
print("Breadth First Search: ",BreadthFirstSearch(dict_graph, start,end))
print ("IterativeDeepening Search: ",IterativeDeepening(dict_graph, start, end))
print ("Depth Limited Search: ",DepthLimitedSearch(dict_graph, start, end,1))


# # Informed search algorithm: Greedy Search

# In[90]:


def GreedySearch(GRAPH,source, destination):
    # HERE THE STRAIGHT LINE DISTANCE VALUES ARE IN REFERENCE 
    straight_line = {                        'Riyadh_Alkabra': 150,                        'Alkhabra': 374,                        'Bukairiah': 380,                        'Mulida': 253,                        'Badaiya': 329,                        'Onaizah': 244,                        'Buriydah': 241,                        'Alrass': 242,                        'Alqurain': 160,                        'Dolaimiah': 193,
                    }
    from queue import PriorityQueue
    priority_queue, visited = PriorityQueue(), {}
    priority_queue.put((straight_line[source], 0, source, [source]))
    visited[source] = straight_line[source]
    while not priority_queue.empty():
        (heuristic, cost, vertex, path) = priority_queue.get()
        if vertex == destination:
            return path,cost
        for next_node in GRAPH[vertex].keys():
            current_cost = cost + GRAPH[vertex][next_node]
            heuristic = current_cost + straight_line[next_node]
            if next_node not in visited or visited[next_node] >= heuristic:
                visited[next_node] = heuristic
                priority_queue.put((heuristic, current_cost, next_node, path + [next_node]))


# In[86]:


print (GreedySearch(dict_graph, start, end))


# In[91]:


print (GreedySearch(dict_graph, start, end))


# In[ ]:




