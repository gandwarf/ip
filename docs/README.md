# Alpha User Guide

This is a project template for a greenfield Java project. It's named after the Java mascot _Alpha_. Given below are instructions on how to use it.

![A demo of Alpha](https://github.com/gandwarf/ip/blob/234e1355ee759c671dcbccc607f0ea00459bf128/A%20demo%20of%20Alpha.png)

Just use 'list' to check all your tasks!
Alpha is a time table robot that provides users with three types of tasks:
  1. Todo
  2. Deadline with a deadline time
  3. Event with start and end time
Users can use 'todo', 'deadline' and 'event' to create correspond items in the time table, use 'delete' to delete the out-of-date items.
And users can also use 'mark' and 'unmark' to set one task as "done", use 'find' to easily pick out the item based on the index
## Adding deadlines

The format of the instruction: deadline **task name** /by **time**

then one deadline will be added to the time table and Alpha will inform you your change in the time table
![A demo of the execution of the deadline command](https://github.com/gandwarf/ip/blob/d75b4389f189bb813cbe851a9de2772737ef494a/a%20demo%20of%20the%20execution%20of%20deadline%20command.png)

## Feature find

The format of the instruction: find **index**

then Alpha will print out the task with the input index

## Feature Duplicate Detection

If you push a task that has already existed in the timetable, Alpha will pop up a window to ask user whether to add this task

![A demo of the execution of Duplicate Detection](https://github.com/gandwarf/ip/blob/5dfe7a2276dddbb8e23485a2e541ba2786ca523c/a%20demo%20of%20the%20execution%20of%20Duplicate%20Detection.png)