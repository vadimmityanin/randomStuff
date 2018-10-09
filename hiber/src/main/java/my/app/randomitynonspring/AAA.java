package my.app.randomitynonspring;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class AAA {


    private static class DTO {
        public List<String> getPhones(){
            return null;
        }
    }

    private  static Optional<Integer> go(){
       return Optional.of(42).map(a->{
            throw new RuntimeException("her na");
        });
    }
//    public static void main(String[] args) throws Exception{
//        ObjectMapper objectMapper = new ObjectMapper();
//        SourceProfile sourceProfile = new SourceProfile();
//        SocialInformation socialInformation = new SocialInformation();
//        MaritalStatus maritalStatus = new MaritalStatus();
//        maritalStatus.setValue("MARIIED FUK");
//
//        socialInformation.setMaritalStatus(maritalStatus);
//        sourceProfile.setSocialInformation(socialInformation);
//
//        System.err.println(objectMapper.writeValueAsString(sourceProfile));
//    }

    public static void main(String[] args) {
        List<String> list = null;
//        CollectionUtils.isEmpty(new AAA.DTO().getPhones())
        ofNullable(new AAA.DTO().getPhones())
                .filter(phones -> ! phones.isEmpty()).ifPresent(phones-> phones.forEach(System.out::println))
                ;


    }
}
