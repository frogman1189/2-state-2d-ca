# 2 State 2D Cellular Automata

This program is a cellular automata project created in java. It was originally intended to simply implement the 4-5 2d cellular rules, but then transformed into a program that could accept any B/S rule string.

It has 3 neighbourhoods implemented:
  - The Moore Neighbourhood
  - The Hexagonal Neighbourhood
  - The Von Neumann Neighbourhood

To compile import into whatever IDE you like and compile the source, using CA_GUI as the main file. I'll write a makefile at some point, but can't be bothered right now.

Example rule strings for the Moore neighbourhood include:
  - B3/S2,3  (Conway's Game Of Life)
  - B5,6,7,8/S4,5,6,7,8  (Cave generation)
