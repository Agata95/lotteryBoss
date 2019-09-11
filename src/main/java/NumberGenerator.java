import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
@Startup
public class NumberGenerator {

    private static final Logger logger = Logger.getLogger(NumberGenerator.class);

    private List<Integer> winningNumbers = drawWinningNumbers();

    private List<Integer> drawWinningNumbers() {
        Set<Integer> numbers = new HashSet<>();
        Random random = new Random();

        while (numbers.size() < 6) {
            numbers.add(random.nextInt(49) + 1);
        }

        return new ArrayList<>(numbers);
    }

    @PostConstruct
    public void generateNumbersOnStart() {
        winningNumbers = drawWinningNumbers();
        logger.info("Liczby wygrywające to: " + winningNumbers);
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

}
