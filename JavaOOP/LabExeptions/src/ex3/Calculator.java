package ex3;

public class Calculator implements ArithmeticOperations{
    @Override
    public double divide (double a, double b) throws DivisionByZeroException {
        if(b == 0){
            throw new DivisionByZeroException("ERROR: Division by 0 is not allowed!!!");
        }
        return a / b;
    }
}
