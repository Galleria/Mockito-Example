WIP
=====================================================

This project is tutorial how to use Mockito and Powermock with Junit for creating unit test (Java project).

First of all, 
I would like to tell you about my English language is fair. if you couldn't understand or would like to change wording please let me know.

When you create unit test on Java project.
Sometimes you need to cut off dependencies such as Database layer, because it is out of control when testing. 

Before explaination Mockito and PowerMock you should know Junit.
--------------------------------------------
Junit : create and assert method.
Mockito : mock/spy/capture method or dependencies and verify calling method.
PowerMockito : mock/spy/capture somethings that mockito couldn't do it.
-------------------------------------------

Mockito can mock/spy/capture, list are as below.

Method          
-----------------------------
| public | private | static |
-----------------------------
|   x    |         |        |
-----------------------------

Variable
-----------------------------
| public | private | static |
-----------------------------
|   x    |         |        |
-----------------------------

Object
-----------------------------
| public | private | static |
-----------------------------
|   x    |    x    |        |
-----------------------------

We use Powermock to add abililies of Mockito, list increase are as below.

Method          
-----------------------------
| public | private | static |
-----------------------------
|        |    x    |    x   |
-----------------------------

Variable
-----------------------------
| public | private | static |
-----------------------------
|        |    x    |    x   |
-----------------------------

Object
-----------------------------
| public | private | static |
-----------------------------
|        |         |   x    |
-----------------------------

Reference.

Mockito
--------------------------------------------------
https://github.com/mockito/mockito 
or 
http://mockito.org/

PowerMock
--------------------------------------------------
https://github.com/jayway/powermock
