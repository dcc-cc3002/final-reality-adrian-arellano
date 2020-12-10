Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control, and a group of 
enemies controlled by the computer.

---

Important for the _Tarea 2_
---------------------------

###_Entregas Parciales_
The first two _entregas parciales_ were made, so that should be considered to the assigned points of this _Tarea_.


The Program
-----------

###Assumptions


- We are going to implement the Thief character as a Character which can equip Swords, Knives
and Bows (Knives instead of Staffs).

- We assume that a Weapon can be equipped by only one character a time.

- We assume that once a character is K.O., it can not be taken out of that state.

- By now, the rest of the functionalities should be like the _Descripción del Proyecto_ says.

###Implemented Functionalities

('Till now) There are only two kinds of objects implemented:
- The Characters: Playable (with sub-types) and not Playable (**Enemies**).
- The Weapons: which can only be equipped by playable Characters.

The current working functionalities are:
> Initialize any object of the described above.

> Get their attributes, like name for both, weight of weapons, damage, etc.

> Add any character to a turn's queue, and get the next turn owner.

> Equip a weapon to a playable character (the process gets done only if the weapon is not
in the hands of another character, and the weapon can be equipped by that kind of character).

In addition to that, the _Tarea 2_ implements the next functionalities.

> The Controller!

> The classes: CharacterCode and WeaponCode which can be used (in the future) by the view to identify
> a Character, or a Weapon (respectively) without having access to the model itself.

> The general functionalities of the controller, like, know if the game has ended, know who won (the
> player or the enemies), identifies the elements of the game and know their _public_ attributes, equip
> a weapon from the view, handle the turns of the game, make an enemy to play it turn by itself, and
> a little more.

> Some of these functionalities were added using the Observer Pattern, or the Factory Pattern. (Other
> Patterns were used too).

---

How to know it works?
---------------------

For now the view part does not exist so, the only way to know if this project is working properly, is
to watch the tests, understand what is happening (for the controller part is very important to have 
in consideration the idea of the classes CharacterCode an WeaponCode), and once the test are understood
running the tests and having only successful test should be enough to know that the program is working
mostly good.

---

##That's All!