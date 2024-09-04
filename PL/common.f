      PROGRAM main
      COMMON /block/ a, b
      REAL a, b

      a = 1.0
      b = 2.0
      CALL sub1
      PRINT *, 'After sub1: a =', a, 'b =', b
      CALL sub2
      PRINT *, 'After sub2: a =', a, 'b =', b
      END

      SUBROUTINE sub1
      COMMON /block/ x, y
      REAL x, y

      x = 10.0
      y = 20.0
      END

      SUBROUTINE sub2
      COMMON /block/ i, j
      INTEGER i, j

      i = 100
      j = 200
      END

