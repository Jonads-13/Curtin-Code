      PROGRAM QuadraticEquation
      REAL A, B, C, DISCRIMINANT, ROOT1, ROOT2

      PRINT *, 'Enter the coefficients A, B, and C:'
      READ *, A, B, C

      DISCRIMINANT = B*B - 4.0*A*C

      IF (DISCRIMINANT .LT. 0.0) THEN
         PRINT *, 'The equation has complex roots.'
      ELSE
         ROOT1 = (-B + SQRT(DISCRIMINANT)) / (2.0*A)
         ROOT2 = (-B - SQRT(DISCRIMINANT)) / (2.0*A)
         PRINT *, 'The roots of the equation are:', ROOT1, ROOT2
      END IF

      STOP
      END

