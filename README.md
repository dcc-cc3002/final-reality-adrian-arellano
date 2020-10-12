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

Important for the _Tarea 1_
---------------------------

###Test Inheritance
This project has test inheritance, and according to the _foro_, that will give some points to this evaluation.

###_Entregas Parciales_
The both _entregas parciales_ were made, so that should be considered to the assigned points of this _Tarea_.


The Program
-----------

###Assumptions


- We are going to implement the Thief character as a Character which can equip Swords, Knives
and Bows (Knives instead of Staffs).

- We assume that a Weapon can be equipped by only one character a time.

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

---

How to know it works?
---------------------

The model is the unique programed part (by now), so if you want to realize that te code works,
you should see at least the abstract classes of the main and the test folder, and the interfaces
to know the programed objects.

After that you should have an idea of the thing that each Interface can do, so now you can see the test
and understand the things that are been tested.

Finally, to see that the program passes the test, and the coverage of the test, you could do the next:

I created a test suite with gradle!, so if you are using Intellij, after the project was build
successfully, you could see, in the up right corner, a test suite prepared to be run with coverage.

Then Intellij will work, and you will know the coverage of every class which I made.

---

##That's All!