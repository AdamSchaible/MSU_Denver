% Dietary by Adam Schaible
% contains a database and a set of rules to generate and confirm
% valid meals for breakfast, lunch, dinner and snacks
% ============================================================
% facts about foods and meals
beverage(black_tea).
beverage(coffee).
beverage(cola).
beverage(milk).
beverage(orange_juice).
beverage(pepsi).
beverage(water).

breakfast_only(bagel).
breakfast_only(coffee).
breakfast_only(eggs).
breakfast_only(oats).
breakfast_only(orange_juice).
breakfast_only(pancakes).

desserts(cheesecake).
desserts(ice_cream).
desserts(pumpkin_pie).
desserts(sugar_cookies).

fruits(apples).
fruits(grapefruit).
fruits(pear).
fruits(strawberries).

proteins(eggs).
proteins(oats).
proteins(seasoned_chicken_breast).
proteins(steak).

salty(pretzels).

snack(chocolate_bar).
snack(popcorn).
snack(pretzels).
snack(celery_sticks).
snack(carrot_sticks).

starches(bagel_with_cream_cheese).
starches(pancakes).
starches(lasagna).
starches(twice_baked_potato).
starches(pretzels).

topping(cheese_dip).
topping(melted_chocolate).

vegatables(celery_sticks).
vegatables(carrot_sticks).

% snack rules..................................................................

snack_salty(X) :- salty(X).
snack_salty(X,Y) :- salty(X), topping(Y).
snack_salty(X,Y) :- salty(Y), topping(X).
snacks(X,Y) :- beverage(X), snack(Y), \+breakfast_only(X), \+vegatables(Y).
snacks(X,Y) :- beverage(Y), snack(X), \+breakfast_only(Y), \+vegatables(X).
snacks(A,B,C) :- beverage(A), snack(B), topping(C),\+breakfast_only(A), vegatables(B).
snacks(A,C,B) :- beverage(A), snack(B), topping(C),\+breakfast_only(A), vegatables(B).
snacks(B,A,C) :- beverage(A), snack(B), topping(C),\+breakfast_only(A), vegatables(B).
snacks(B,C,A) :- beverage(A), snack(B), topping(C),\+breakfast_only(A), vegatables(B).
snacks(C,A,B) :- beverage(A), snack(B), topping(C),\+breakfast_only(A), vegatables(B).
snacks(C,B,A) :- beverage(A), snack(B), topping(C),\+breakfast_only(A), vegatables(B).

is_this_a_salty_snack(A) :- (snack_salty(A), write('This is a salty snack!'));
							write('This is not a salty snack!'),(A==A).
							
is_this_a_salty_snack(A,B) :- (snack_salty(A,B), write('This is a salty snack!'));
							write('This is not a salty snack!'),(A==A).

is_this_a_non_salty_snack(A,B,C) :- (snacks(A,B,C), write('This is a non-salty snack!'));
							write('This is not a non-salty snack!'),(A==A).
							


% breakfast rules..(3 COMBOS, RULES TO FIND CORRECT MEALS REGARDLESS OF ORDER ENTERED).......
breakfast(A,B,C) :- beverage(A), proteins(B), starches(C),
					\+(\+breakfast_only(A)), \+(\+breakfast_only(B)), \+(\+breakfast_only(C)).

breakfast(A,C,B) :- beverage(A), proteins(B), starches(C),
					\+(\+breakfast_only(A)), \+(\+breakfast_only(B)), \+(\+breakfast_only(C)).
					
breakfast(B,A,C) :- beverage(A), proteins(B), starches(C),
					\+(\+breakfast_only(A)), \+(\+breakfast_only(B)), \+(\+breakfast_only(C)).

breakfast(B,C,A) :- beverage(A), proteins(B), starches(C),
					\+(\+breakfast_only(A)), \+(\+breakfast_only(B)), \+(\+breakfast_only(C)).

breakfast(C,A,B) :- beverage(A), proteins(B), starches(C),
					\+(\+breakfast_only(A)), \+(\+breakfast_only(B)), \+(\+breakfast_only(C)).

breakfast(C,B,A) :- beverage(A), proteins(B), starches(C),
					\+(\+breakfast_only(A)), \+(\+breakfast_only(B)), \+(\+breakfast_only(C)).					
					
% lunch rules......(3 COMBOS, RULES TO FIND CORRECT MEALS REGARDLESS OF ORDER ENTERED).......

lunch(A,B,C) :- beverage(A), starches(B), fruits(C),
					\+breakfast_only(A), \+breakfast_only(B).
					
lunch(A,C,B) :- beverage(A), starches(B), fruits(C),
					\+breakfast_only(A), \+breakfast_only(B).	

lunch(B,A,C) :- beverage(A), starches(B), fruits(C),
					\+breakfast_only(A), \+breakfast_only(B).

lunch(B,C,A) :- beverage(A), starches(B), fruits(C),
					\+breakfast_only(A), \+breakfast_only(B).

lunch(C,A,B) :- beverage(A), starches(B), fruits(C),
					\+breakfast_only(A), \+breakfast_only(B).

lunch(C,B,A) :- beverage(A), starches(B), fruits(C),
					\+breakfast_only(A), \+breakfast_only(B).				

% dinner rules(without dessert).(3 COMBOS, RULES TO FIND CORRECT MEALS REGARDLESS OF ORDER ENTERED).......

dinner(A,B,C) :- beverage(A), proteins(B), starches(C),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(A,C,B) :- beverage(A), proteins(B), starches(C),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(B,A,C) :- beverage(A), proteins(B), starches(C),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).
					
dinner(B,C,A) :- beverage(A), proteins(B), starches(C),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).	

dinner(C,A,B) :- beverage(A), proteins(B), starches(C),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(C,B,A) :- beverage(A), proteins(B), starches(C),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).					
					
% dinner rules(with dessert).(4 COMBOS, RULES TO FIND CORRECT MEALS REGARDLESS OF ORDER ENTERED).......

dinner(A,B,C,D) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).
					
dinner(A,B,D,C) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(A,C,B,D) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).	

dinner(A,C,D,B) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(A,D,B,C) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(A,D,C,B) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(B,A,C,D) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(B,A,D,C) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(B,C,A,D) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(B,C,D,A) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(B,D,A,C) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(B,D,C,A) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(C,A,B,D) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(C,A,D,B) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(C,B,A,D) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(C,B,D,A) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(C,D,A,B) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(C,D,B,A) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(D,A,B,C) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(D,A,C,B) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(D,B,A,C) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).	

dinner(D,B,C,A) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

dinner(D,C,A,B) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).	

dinner(D,C,B,A) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).				

% rules for is some combination a meal?.........................................
is_this_combo_a_meal(A) :- write('This is not a meal!'),(A\==A).
is_this_combo_a_meal(A,B) :- write('This is not a meal!'),(A==B),(A\==B).
is_this_combo_a_meal(A,B,C) :- ((dinner(A,B,C); lunch(A,B,C); breakfast(A,B,C)), write('This is a meal!'));write('This is not a meal').
is_this_combo_a_meal(A,B,C,D) :- dinner(A,B,C,D), write('This is a meal!');write('This is not a meal').

% rules for is some combination breakfast, lunch or dinner?

is_this_breakfast(A) :- write('This is not a breakfast combo!'),(A\==A).
is_this_breakfast(A,B) :- write('This is not a breakfast combo!'),(A==B),(A\==B).
is_this_breakfast(A,B,C) :- breakfast(A,B,C), write('This is a breakfast combo!');write('This is not a breakfast combo').

is_this_lunch(A) :- write('This is not a lunch combo!'),(A\==A).
is_this_lunch(A,B) :- write('This is not a lunch combo!'),(A==B),(A\==B).
is_this_lunch(A,B,C) :- lunch(A,B,C), write('This is a lunch combo!');write('This is not a lunch combo').

is_this_dinner(A) :- write('This is not a dinner combo!'),(A\==A).
is_this_dinner(A,B) :- write('This is not a dinner combo!'),(A==B),(A\==B).
is_this_dinner(A,B,C) :- dinner(A,B,C), write('This is a dinner combo!');write('This is not a dinner combo').
is_this_dinner(A,B,C,D) :- dinner(A,B,C,D), write('This is a dinner combo!');write('This is not a dinner combo').

% rules for what meals can you make with some food item
make_breakfast_with(A,B,C) :- write('Breakfast Combo!'), beverage(A), proteins(B), starches(C),
					\+(\+breakfast_only(A)), \+(\+breakfast_only(B)), \+(\+breakfast_only(C)).
					
make_lunch_with(A,B,C) :- beverage(A), starches(B), fruits(C),
					\+breakfast_only(A), \+breakfast_only(B).

make_a_dinner_with(A,B,C) :- beverage(A), proteins(B), starches(C),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).

make_a_dinner_plus_dessert_with(A,B,C,D) :- beverage(A), proteins(B), starches(C), desserts(D),
					\+breakfast_only(A), \+breakfast_only(B), \+breakfast_only(C).					