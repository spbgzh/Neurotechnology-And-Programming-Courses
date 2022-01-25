package analyzer;

import data.Repository;

import javax.servlet.http.HttpServletRequest;

public interface Analyzer {
    int getExpressionResult(String expression, HttpServletRequest req, Repository repository);
}