import java.io.Serializable;

public class Car implements Serializable{
    String numberplate;

    @Override
    public String toString()
    {
        return numberplate;
    }
}