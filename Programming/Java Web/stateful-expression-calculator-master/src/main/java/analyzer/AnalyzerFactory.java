package analyzer;

import analyzer.lexemeAnalyzer.AnalyzerImpl;

public class AnalyzerFactory {
    public Analyzer getLexemeAnalyzer() {
        return new AnalyzerImpl();
    }
}