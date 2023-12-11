package com.nighthawk.spring_portfolio.fibo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fibo")
public class FiboApiController {

    @Autowired

    @GetMapping("/iterative")
    public String getFibonacciIterative() {
        FibonacciIterativeVisualization.main(null);
        return "Fibonacci Iterative visualization completed.";
    }

    @GetMapping("/memoization")
    public String getFibonacciMemoization() {
        FibonacciMemoizationVisualization.main(null);
        return "Fibonacci Memoization visualization completed.";
    }

    @GetMapping("/recursive")
    public String getFibonacciRecursive() {
        FibonacciRecursiveVisualization.main(null);
        return "Fibonacci Recursive visualization completed.";
    }
}
