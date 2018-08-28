package my.app.randomitynonspring;

import java.util.Optional;

public class AAA {

    private  static Optional<Integer> go(){
       return Optional.of(42).map(a->{
            throw new RuntimeException("her na");
        });
    }
    public static void main(String[] args) {
        System.err.println(go());
    }
}
