%facts
parent(sam,james).
parent(sam,elizabeth).
parent(james,catherine).
parent(james,bob).
parent(bob,amy).
parent(amy,joe).
parent(amy,sarah).
%rules

descendent(D,A):-parent(A,D).
descendent(D,A):-parent(P,D),descendant(P,A).
ancestor(A,D):-descendent(D,A). 

full_siblings(A,B):-
	parent(F,A),parent(F,B),
	parent(M,A),parent(M,B),
	A\=B.
