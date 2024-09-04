      PROGRAM Calculator
      REAL NUM1, NUM2, RESULT
      INTEGER CHOICE

10    PRINT *, 'Simple Calculator Menu'
      PRINT *, '1. Addition'
      PRINT *, '2. Subtraction'
      PRINT *, '3. Multiplication'
      PRINT *, '4. Division'
      PRINT *, '5. Exit'
      PRINT *, 'Enter your choice (1-5):'
      READ *, CHOICE

      IF (CHOICE .EQ. 5) GOTO 100

      PRINT *, 'Enter the first number:'
      READ *, NUM1
      PRINT *, 'Enter the second number:'
      READ *, NUM2

      IF (CHOICE .EQ. 1) GOTO 20
      IF (CHOICE .EQ. 2) GOTO 30
      IF (CHOICE .EQ. 3) GOTO 40
      IF (CHOICE .EQ. 4) GOTO 50
      GOTO 60

20    RESULT = NUM1 + NUM2
      PRINT *, 'The result of addition is:', RESULT
      GOTO 10

30    RESULT = NUM1 - NUM2
      PRINT *, 'The result of subtraction is:', RESULT
      GOTO 10

40    RESULT = NUM1 * NUM2
      PRINT *, 'The result of multiplication is:', RESULT
      GOTO 10

50    IF (NUM2 .EQ. 0.0) THEN
         PRINT *, 'Error: Division by zero is not allowed.'
         GOTO 10
      ELSE
         RESULT = NUM1 / NUM2
         PRINT *, 'The result of division is:', RESULT
         GOTO 10
      END IF

60    PRINT *, 'Invalid choice. Please try again.'
      GOTO 10

100   PRINT *, 'Exiting the calculator. Goodbye!'
      STOP
      END

