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

Important for the _Tarea 3_
---------------------------

###_Entregas Parciales_
The first _entrega parcial_ for this _Tarea 2_ was made, so that should be considered to the
assigned points of this _Tarea_.


How To play
-----------

To play my version of Final Reality you have to run this program, specifically the file 
*FinalReality.java*.

Let me to explain the gui, after it starts you should press the start button, which is in the bottom
of the window. Immediately after that, you will start the game, and one of your pre-created
characters should play, you have to choose a weapon (every character start without a weapon 
equipped), to choose a weapon there are two lateral arrows (one pointing to the left, and the other
to the right) which you could press to see all the available weapons.

In the configuration that I made you will always have a weapon to be equipped to your character, to 
actually equip a weapon you have to press the *EQUIP* button, and after that you will see that your
character has that weapon as its current equipped weapon.

You could try to equip every weapon available, but, for example, a White wizard can only be equipped
with a Staff, when you try to equip a not compatible weapon, the program will tell you, and, (clearly)
the weapon will not be equipped.

After you took your favorite weapon for your current character, you have to decide which enemy to 
attack, for this the process is equals to the equipping process, but after you press the *ATTACK*
button, your turn will be over, so decide well!

I you try to attack, and you don't have a weapon equipped, the program will tell you and the attack
will not be effectuated.

This pre-configuration could result in a win run, or you could lose, so with this you could see what
happen in any case.

I think that's all :D


The Program
-----------

###Assumptions


- We are going to implement the Thief character as a Character which can equip Swords, Knives
and Bows (Knives instead of Staffs).

- We assume that a Weapon can be equipped by only one character a time.

- We assume that once a character is K.O., it can not be taken out of that state.

- In terms of implementation we decided the next:

 At the first phase of the game we won't only see the character at the top of the queue, we are
 going to take it out of the queue.

 At the second phase of the game we won't take out the character of the queue, instead we will
 restart the turn owner of the game.

- When a playable character gets knocked out, her/his equipped weapon keeps equipped.  

- By now, the rest of the functionalities should be like the _Descripción del Proyecto_ says.

--------------------------------------
####Tackling the problem of the phases
Just as they are made, the phases a planted inside the PDF with the description of the project,
these phases of a turn, sincerely stink. There are a lot of reasons to that, but the one which I
think is the worst, is where we decide to use the character of the top of the turns queue, and use
it like the turn owner, and *at the end of the turn* take it out from the queue, and to make matters
worst, after that put say that the character has to wait for its next turn.

Why is that a bad thing?
Well, let me to explain it: the BlockingQueue is a particular object which works with threads, for
example, to add a new object to the queue after an amount of time, so, working with threads and
knowing when there is a new member of in the queue, gives this particular object, the ability to
work as an (in our terms) "Observer", so the queue can know when a new element is inside all by
itself, but the method which have that feature can only take the element out; they cannot only watch
it, so that phase of the game will either obligate me to add a new unnecessary observer either use
another class to make this; which all seems like a bad idea for me. So here I'm going define my own
phases of the game.

> 0.- The phase zero,has the sole purpose to be the first phase of the game, and works (talking 
> about transitions), as the phase 3 (its only transition is to the phase 1).

> 1.- We take the first character of the queue out and, if it is not K.O., we set it as the current 
> owner of the turn (we wait for the queue to have a character if is empty [this is automatically
> made by the queue]).

> 2.- Here and _only_ here, the turn owner (any character) has the right to equip a weapon how many
> times it want and attack, but it can only attack once; if it is not this phase, is illegal trying
> to do any of this.
>> Immediately after this character's attack:
>>> The character waits for its new turn.
>> 
>>> The turn owner un nullified (from now on there is not a turn owner).

> 3.- Back to the phase 1; in this phase there is no turn owner, so it is necessary to have this as
> a phase.

For now this state pattern is more than enough for the game functionalities, so:

Why to do a state patter?

Let me answer this, may be now there are only 2 states, but in the future, with the paralysis,
the burning, and the poisson effects we will need, at least, 2 more states which will not work as a
lineal path, in conclusion:

We implement the state patter not because we need it, but because this will help us to futures
updates of the program.



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