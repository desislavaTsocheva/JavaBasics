import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoPartsService {
    private final List<AutoParts> autoParts=new ArrayList<>();
    private List<String> request=new ArrayList<>();
    public synchronized List<AutoParts>getAutoParts() {
        return autoParts;
    }

    public synchronized void makeRequest(int id,int amount){
        String req="";
        for(AutoParts a:autoParts){
            if(a.getId()==id&&a.getAmount()>=amount){
                req=String.format("Заяка за %s е приета успешно. ",a.getName());
                System.out.println(req);
            }
            else{
                req="Вашата поръчка не може да бъде изпълнена";
            }
        }
        request.add(req);
    }

    public synchronized List<String>getRequests(){
        int i=0;
        for(String a:request){
            i++;
            return Collections.singletonList(String.format("Поръчка номер %d е със статус Очаква потвърждение", i));
        }
        return Collections.emptyList();
    }
}
