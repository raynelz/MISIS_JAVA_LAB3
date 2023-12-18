package io.raynelz.task1;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;


interface Strategy {
    @NotNull
    String process(@NotNull List<String> strings);
}

interface StrategyService {
    String exec(List<String> strings);
    StrategyService setStrategy(@NotNull Strategy strategy);
}

class CommaSeparatedStrategy implements Strategy {

    @NotNull
    @Override
    public String process(@NotNull List<String> strings) {
       return StringUtils.join(strings, ",");
    }
}

class SemicolonSepararedStrategy implements Strategy {
    @NotNull
    @Override
    public String process(@NotNull List<String> strings) {
        return StringUtils.join(strings, ";");
    }
}

class StrategyServiceImpl implements StrategyService {
    private Strategy strategy;

    public StrategyServiceImpl(@NotNull Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    @NotNull
    public String exec(@NotNull List<String> strings) {
        String result = "";
        if(strings.isEmpty())
            return result;

        return strategy.process(strings);
    }

    public StrategyService setStrategy(@NotNull Strategy strategy) {
        this.strategy = strategy;
        return this;
    }
}

public class Application {
    public static void main(String[] args) {
        StrategyServiceImpl service = new StrategyServiceImpl(new CommaSeparatedStrategy());
        List<String> strings = Arrays.asList("Hello", "World", "Raynelz");
        String result = service.exec(strings);
        System.out.println("Result: " + result);
    }
}