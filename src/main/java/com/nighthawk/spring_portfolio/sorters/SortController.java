package com.nighthawk.spring_portfolio.sorters;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/sort")
public class SortController {

    @PostMapping("/benchmark")
    public ResponseEntity<?> benchmarkSort(@RequestParam int length, @RequestParam String method) {
        Sorter sorter;
        switch (method.toLowerCase()) {
            case "binary":
                sorter = new BinarySorter();
                break;
            case "bubble":
                sorter = new BubbleSorter();
                break;
            case "merge":
                sorter = new MergeSorter();
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid sorting method");
        }

        // Generate data and benchmark
        double[][] xData = MockDataGenerator.generateXData(length);
        double[] yData = MockDataGenerator.generateYData(xData);
        long time = sorter.timeSorting(yData);

        // Save the benchmark result for future use
        // TODO: Implement a mechanism to store benchmark results

        // Return the benchmark result
        return ResponseEntity.ok(new BenchmarkResult(length, time));
    }

    // Inner class to structure the response
    static class BenchmarkResult {
        public int length;
        public long time;

        public BenchmarkResult(int length, long time) {
            this.length = length;
            this.time = time;
        }
    }
}