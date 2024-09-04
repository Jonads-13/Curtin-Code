program calculator
  implicit none
  real :: num1, num2, result
  integer :: choice

  do
     print *, 'Simple Calculator Menu'
     print *, '1. Addition'
     print *, '2. Subtraction'
     print *, '3. Multiplication'
     print *, '4. Division'
     print *, '5. Exit'
     print *, 'Enter your choice (1-5):'
     read *, choice

     if (choice == 5) exit

     print *, 'Enter the first number:'
     read *, num1
     print *, 'Enter the second number:'
     read *, num2

     select case (choice)
        case (1)
           result = num1 + num2
           print *, 'The result of addition is:', result
        case (2)
           result = num1 - num2
           print *, 'The result of subtraction is:', result
        case (3)
           result = num1 * num2
           print *, 'The result of multiplication is:', result
        case (4)
           if (num2 == 0.0) then
              print *, 'Error: Division by zero is not allowed.'
           else
              result = num1 / num2
              print *, 'The result of division is:', result
           end if
        case default
           print *, 'Invalid choice. Please try again.'
     end select
  end do

  print *, 'Exiting the calculator. Goodbye!'
end program calculator

