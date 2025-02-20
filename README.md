# Alpha User Guide

![A demo of Alpha](https://raw.githubusercontent.com/gandwarf/ip/a28a8a4516162d58e6f64f5b25879dd48bf8def8/docs/Ui.png)

## Wellcome User
Feeling overwhelmed by millions of works? Let Alpha help you deal with it!
Equipped with 
- text-based📄
- No entry barrier🎯
- super fast⚡⚡
Alpha will do its best

Let's get start:

1. download the newest release
2. run it using `java -jar alpha.java`
3. add all your tasks
4. let it git rid of all the trouble for you

This is a project template for a greenfield Java project. It's named after the Java mascot _Alpha_. Given below are instructions on how to use it.
Just use `list` to check all your tasks!
Alpha is a time table robot that provides users with three types of tasks:
  1. Todo
  2. Deadline with a deadline time
  3. Event with start and end time
Users can use `todo`, `deadline` and `event` to create correspond items in the time table, use `delete` to delete the out-of-date items.
And users can also use `mark` and `unmark` to set one task as "done", use `find` to easily pick out the item based on the index
## Adding tasks

you may add a deadline bt the command: 
`deadline task name /by time`
the other two types of tasks are added by: 
`todo taskname`
`event eventname /from starttime /to endtime`
then one task will be added to the time table and Alpha will inform you your change in the time table like
![A demo of the execution of the deadline command](https://raw.githubusercontent.com/gandwarf/ip/df1e555e37a704b7e274903fccb7c0ac210463a2/a%20demo%20of%20Alpha.jpg)

## Feature find

The format of the instruction: 
`find index`

then Alpha will print out the task with the input index

## Feature mark and unmark

you may mark or unmark a task by:
`mark taskindex`
`unmark taskindex`

## Feature Duplicate Detection

If you push a task that has already existed in the timetable, Alpha will pop up a window to ask user whether to add this task

