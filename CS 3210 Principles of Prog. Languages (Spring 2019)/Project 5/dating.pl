% By Adam Schaible
% Matches up individuals based on their characteristics
% =====================================================

% facts about dating candidates
% men
man(andrew).
man(charlie).
man(christopher).
man(daniel).
man(david).
man(frank).
man(george).
man(james).
man(john).
man(joseph).
man(kevin).
man(mark).
man(michael).
man(paul).
man(peter).
man(richard).
man(robert).
man(scott).
man(sean).
man(thomas).
man(william).

% woman
woman(ally).
woman(andrea).
woman(barbara).
woman(catherine).
woman(dorothy).
woman(elizabeth).
woman(heather).
woman(jennifer).
woman(karen).
woman(linda).
woman(lisa).
woman(margaret).
woman(mary).
woman(megan).
woman(nancy).
woman(patricia).
woman(samantha).
woman(sandy).
woman(sarah).
woman(shawna).
woman(susan).

% age

age(andrew,twenties).
age(charlie,twenties).
age(christopher,thirties).
age(daniel,fourty_thru_fifty_five).
age(david,fifty_five_thru_seventy).
age(frank,seventy_plus).
age(george,twenties).
age(james,thirties).
age(john,fourty_thru_fifty_five).
age(joseph,fifty_five_thru_seventy).
age(kevin,twenties).
age(mark,thirties).
age(michael,fourty_thru_fifty_five).
age(paul,twenties).
age(peter,twenties).
age(richard,twenties).
age(robert,thirties).
age(scott,thirties).
age(sean,fourty_thru_fifty_five).
age(thomas,fourty_thru_fifty_five).
age(william,fourty_thru_fifty_five).

age(ally, twenties).
age(andrea,twenties).
age(barbara,thirties).
age(catherine,thirties).
age(dorothy,twenties).
age(elizabeth,fourty_thru_fifty_five).
age(heather,seventy_plus).
age(jennifer,twenties).
age(karen,thirties).
age(linda,fourty_thru_fifty_five).
age(lisa,thirties).
age(margaret,twenties).
age(mary,twenties).
age(megan,thirties).
age(nancy,fourty_thru_fifty_five).
age(patricia,fourty_thru_fifty_five).
age(samantha,twenties).
age(sandy,fourty_thru_fifty_five).
age(sarah,twenties).
age(shawna,fifty_five_thru_seventy).
age(susan,fourty_thru_fifty_five).

% profession

profession(andrew,office_clerk).
profession(charlie,janitor).
profession(christopher,salesperson).
profession(daniel,office_clerk).
profession(david,salesperson).
profession(frank,janitor).
profession(george,office_clerk).
profession(james,salesperson).
profession(john,manager).
profession(joseph,office_clerk).
profession(kevin,manager).
profession(mark,janitor).
profession(michael,manager).
profession(paul,manager).
profession(peter,salesperson).
profession(richard,manager).
profession(robert,manager).
profession(scott,office_clerk).
profession(sean,janitor).
profession(thomas,manager).
profession(william,salesperson).

profession(ally,manager).
profession(andrea,office_clerk).
profession(barbara,salesperson).
profession(catherine,manager).
profession(dorothy,janitor).
profession(elizabeth,salesperson).
profession(heather,salesperson).
profession(jennifer,manager).
profession(karen,office_clerk).
profession(linda,janitor).
profession(lisa,manager).
profession(margaret,salesperson).
profession(mary,office_clerk).
profession(megan,janitor).
profession(nancy,manager).
profession(patricia,salesperson).
profession(samantha,office_clerk).
profession(sandy,janitor).
profession(sarah,office_clerk).
profession(shawna,manager).
profession(susan,salesperson).

% likes travelling

who_likes_travelling(andrew,yes).
who_likes_travelling(charlie,no).
who_likes_travelling(christopher,yes).
who_likes_travelling(daniel,yes).
who_likes_travelling(david,no).
who_likes_travelling(frank,no).
who_likes_travelling(george,yes).
who_likes_travelling(james,no).
who_likes_travelling(john,yes).
who_likes_travelling(joseph,yes).
who_likes_travelling(kevin,yes).
who_likes_travelling(mark,no).
who_likes_travelling(michael,no).
who_likes_travelling(paul,yes).
who_likes_travelling(peter,yes).
who_likes_travelling(richard,no).
who_likes_travelling(robert,yes).
who_likes_travelling(scott,no).
who_likes_travelling(sean,yes).
who_likes_travelling(thomas,yes).
who_likes_travelling(william,no).

who_likes_travelling(ally,no).
who_likes_travelling(andrea,yes).
who_likes_travelling(barbara,yes).
who_likes_travelling(catherine,no).
who_likes_travelling(dorothy,no).
who_likes_travelling(elizabeth,yes).
who_likes_travelling(heather,no).
who_likes_travelling(jennifer,yes).
who_likes_travelling(karen,no).
who_likes_travelling(linda,yes).
who_likes_travelling(lisa,no).
who_likes_travelling(margaret,yes).
who_likes_travelling(mary,no).
who_likes_travelling(megan,no).
who_likes_travelling(nancy,yes).
who_likes_travelling(patricia,no).
who_likes_travelling(samantha,no).
who_likes_travelling(sandy,yes).
who_likes_travelling(sarah,no).
who_likes_travelling(shawna,yes).
who_likes_travelling(susan,yes).

% entrepreneurial

entrepreneurial(andrew,no).
entrepreneurial(charlie,no).
entrepreneurial(christopher,no).
entrepreneurial(daniel,yes).
entrepreneurial(david,no).
entrepreneurial(frank,no).
entrepreneurial(george,no).
entrepreneurial(james,no).
entrepreneurial(john,yes).
entrepreneurial(joseph,no).
entrepreneurial(kevin,no).
entrepreneurial(mark,no).
entrepreneurial(michael,no).
entrepreneurial(paul,no).
entrepreneurial(peter,yes).
entrepreneurial(richard,no).
entrepreneurial(robert,no).
entrepreneurial(scott,no).
entrepreneurial(sean,no).
entrepreneurial(thomas,no).
entrepreneurial(william,no).

entrepreneurial(ally,no).
entrepreneurial(andrea,yes).
entrepreneurial(barbara,no).
entrepreneurial(catherine,no).
entrepreneurial(dorothy,no).
entrepreneurial(elizabeth,no).
entrepreneurial(heather,no).
entrepreneurial(jennifer,yes).
entrepreneurial(karen,no).
entrepreneurial(linda,no).
entrepreneurial(lisa,no).
entrepreneurial(margaret,no).
entrepreneurial(mary,yes).
entrepreneurial(megan,no).
entrepreneurial(nancy,no).
entrepreneurial(patricia,no).
entrepreneurial(samantha,no).
entrepreneurial(sandy,yes).
entrepreneurial(sarah,no).
entrepreneurial(shawna,yes).
entrepreneurial(susan,no).

% table below used to weed out duplicates
:- table who_should_date/2.

% ===========================================================================
% The who_should_date checks if the potential daters are the same age,
% are not the same person, one is a man and the other is a woman, 
% are considered a match if they enjoy traveling or are entrepreneurial or 
% have the same type of profession. 
%============================================================================

who_should_date(X,Y) :-  
	age(X,R),age(Y,R),(X \== Y),((man(X),woman(Y));
	(man(Y),woman(X))),((who_likes_travelling(X,yes),(who_likes_travelling(Y,yes)));
	(entrepreneurial(X,yes),(entrepreneurial(Y,yes)));
	(profession(X,manager),(profession(Y,manager)));
	(profession(X,salesperson),(profession(Y,salesperson)));
	(profession(X,office_clerk),(profession(Y,office_clerk)));
	(profession(X,janitor),(profession(Y,janitor)))).
	
% should_they_date reveals whether two individuals should date 	
should_they_date(X,Y) :- who_should_date(X,Y), write('YES, THEY SHOULD DATE').
should_they_date(X,Y) :- \+who_should_date(X,Y), write('NO, THEY SHOULD NOT DATE').	




 

