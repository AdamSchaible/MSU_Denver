likes(alice, bob).
likes(bob, carol).
likes(james, mary).
likes(mary, james).

dating(X,Y) :-
	likes(X,Y),
	likes(Y,X).

friendship(X,Y):-
	likes(X,Y);
	likes(Y,X).

vegetables(celery; squash; potato).
fruits(apple;orange;banana).
desserts(cake; chocolate; ice cream).
snacks(chips; cookies; chocolate).
proteins(meat; wheat; soy; fish).
starches(flour; wheat).
drinks(water; soda; beer).

		