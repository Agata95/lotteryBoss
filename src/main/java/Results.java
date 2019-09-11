import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/results")
public class Results {

    @Inject
    private NumberGenerator numberGenerator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLotteryResults(
            @Valid @NotNull @Size(min = 6, max = 6)
                    List<Integer> userNumbers) {
//        @Size kontroluje, czy użytkownik podał 6 liczb

        boolean success = isSuccess(userNumbers, numberGenerator.getWinningNumbers());
        if (success) {
            return Response.ok("Wylosowane liczby to: " + userNumbers
                    + " Wygrałeś milion!").build();
        }
        return Response.ok("Wylosowane liczby to: " + userNumbers
                + " Niestety nie wygrałeś, spróbuj jeszcze raz").build();
    }

    private boolean isSuccess(List<Integer> numbers, List<Integer> winningNumbers) {
        return numbers.stream().anyMatch(n -> winningNumbers.contains(n));
    }

}
