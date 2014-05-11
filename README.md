# Compiler for the X language

Final project for CSC 413: Software Development.

An interpreter and debugger designed from bytecodes of the X language.

## Usage Notes

To run the project from the command line type the following:

java -jar "Interpreter.jar" <filename.x.cod>

to run the interpreter and

java -jar "Interpreter.jar" -d <filename no extension>

to run the debugger

## Debugger commands

[break]  Set/Clear breakpoints
[fct]    Display the current function
[cont]   Continue execution
[var]    Display variables
[stpout] Step out of the current activation of a function
[stpovr] Step over a line
[stpin]  Step into a function
[list]   List breakpoints
[call]   Print callstack
[trace]  Set function tracing
[src]    Display the source code (with breakpoints)
[q]      Quit
