      PROGRAM Calculator
      REAL NUM1, NUM2, RESULT
      INTEGER CHOICE

10    WRITE (*, 100)
100   FORMAT ('Simple Calculator Menu',/, 
     &        '1. Addition',/,
     &        '2. Subtraction',/,
     &        '3. Multiplication',/,
     &        '4. Division',/,
     &        '5. Exit',/,
     &        'Enter your choice (1-5):')
      READ *, CHOICE

      IF (CHOICE .EQ. 5) GOTO 200

      WRITE (*, 110)
110   FORMAT ('Enter the first number:')
      READ *, NUM1
      WRITE (*, 120)
120   FORMAT ('Enter the second number:')
      READ *, NUM2

      IF (CHOICE .EQ. 1) GOTO 20
      IF (CHOICE .EQ. 2) GOTO 30
      IF (CHOICE .EQ. 3) GOTO 40
      IF (CHOICE .EQ. 4) GOTO 50
      GOTO 60

20    RESULT = NUM1 + NUM2
      WRITE (*, 130) RESULT
130   FORMAT ('The result of addition is:', F10.2)
      GOTO 10

30    RESULT = NUM1 - NUM2
      WRITE (*, 140) RESULT
140   FORMAT ('The result of subtraction is:', F10.2)
      GOTO 10

40    RESULT = NUM1 * NUM2
      WRITE (*, 150) RESULT
150   FORMAT ('The result of multiplication is:', F10.2)
      GOTO 10

50    IF (NUM2 .EQ. 0.0) THEN
         WRITE (*, 160)
160      FORMAT ('Error: Division by zero is not allowed.')
         GOTO 10
      ELSE
         RESULT = NUM1 / NUM2
         WRITE (*, 170) RESULT
170      FORMAT ('The result of division is:', F10.2)
         GOTO 10
      END IF

60    WRITE (*, 180)
180   FORMAT ('Invalid choice. Please try again.')
      GOTO 10

200   WRITE (*, 190)
190   FORMAT ('Exiting the calculator. Goodbye!')
      STOP
      END

