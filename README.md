# COMP 2210: Assignment 4

## Problem Overview
This assignment requires you to implement a set collection using a doubly-linked list as the underlying data structure. You are provided with the Set interface and a shell of the LinkedSet implementing class. You must not change anything in the Set interface, but you must create correct implementations of the methods in the LinkedSet class. In doing so you are allowed to add any number of private methods and nested classes that you need, but you may not create or use any other top-level class and you may not create any public method. You must also use without modification the existing fields of the LinkedSet class.

The Set interface is generic and makes no restrictions on the type that it can contain. The LinkedSet class is also generic, but it does make a restriction on the type variable: Any type bound by a client to T must be a class that implements the Comparable interface for that type. Thus, there is a natural order on the values stored in an LinkedSet, but not (necessarily) on those stored in another class that implements the Set interface. This is an important distinction, so pause here long enough to make sure you understand this point.

The following sections describe each method to be implemented, organized according to the methods appearing in the Set interface and those specific to the LinkedSet class. Each method’s behavior is described below and in comments in the provided source code. You must read both. Note that in addition to correctness, your code must meet the stated performance requirements.

## Grade
95/100
