package servlet;

import analyzer.AnalyzerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

import static data.Repository.SINGLETON_REP;

@WebServlet("/calc/*")
public class ExpressionServlet extends HttpServlet {
    private static final String EXPRESSION = "expression";
    private static final String EMPTY_STRING = "";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String DIVIDE = "/";
    private static final String MULTIPLY = "*";

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        SINGLETON_REP.getParametersData().get(req.getSession().getId()).remove(getNameParameter(req));
        resp.setStatus(204);//expression deleted
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        try {
            printWriter.printf(String.valueOf(getExprResult(req)));
        } catch (Exception e) {
            resp.setStatus(409); //bad expression result
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(req.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String paramValue = bufferedReader.readLine();
        addParametersToRepository(req, resp, paramValue);
    }

    private void addParametersToRepository(HttpServletRequest req, HttpServletResponse resp, String paramValue) {
        if (!SINGLETON_REP.getParametersData().containsKey(req.getSession().getId())) {
            SINGLETON_REP.getParametersData().put(req.getSession().getId(), new ConcurrentHashMap<>());
        }
        setStatusCodeToPutMethod(req, resp, paramValue);
        if (!isExpressionHasBadFormat(paramValue, req) && !isParameterHasOverLimitValue(paramValue)) {
            SINGLETON_REP.getParametersData().get(req.getSession().getId()).put(getNameParameter(req), paramValue);
        }
    }

    private void setStatusCodeToPutMethod(HttpServletRequest req, HttpServletResponse resp, String paramValue) {
        if (getValueFromRep(req) == null) {
            resp.setStatus(201); //new expression
        } else if (isParameterHasOverLimitValue(paramValue)) {
            resp.setStatus(403); //over limit parameter value
        } else if (isExpressionHasBadFormat(paramValue, req)) {
            resp.setStatus(400); //expression bad format
        } else {
            resp.setStatus(200);
        }
    }

    private boolean isParameterHasOverLimitValue(String paramValue) {
        return isDigit(paramValue) && Math.abs(Integer.parseInt(paramValue)) > 10000;
    }

    private boolean isDigit(String paramValue) {
        char[] paramValueChars = paramValue.toCharArray();
        ArrayList<Character> arrayList = new ArrayList<>();
        for (char paramValueChar : paramValueChars) {
            if (paramValueChar != '-') {
                arrayList.add(paramValueChar);
            }
        }
        return arrayList.stream().allMatch(Character::isDigit);
    }

    private String getValueFromRep(HttpServletRequest req) {
        return SINGLETON_REP.getParametersData().get(req.getSession().getId()).get(getNameParameter(req));
    }

    private boolean isExpressionHasBadFormat(String paramValue, HttpServletRequest req) {
        return getNameParameter(req).equals(EXPRESSION) &&
                !(paramValue.contains(PLUS) || paramValue.contains(MINUS) ||
                        paramValue.contains(DIVIDE) || paramValue.contains(MULTIPLY));
    }

    private String getNameParameter(HttpServletRequest req) {
        return req.getPathInfo().substring(1);
    }

    private int getExprResult(HttpServletRequest req) {
        String[] expression = getExpression(req.getSession().getId());
        String expressionWithValue = getExpressionWithValue(expression, req.getSession().getId());
        return new AnalyzerFactory().getLexemeAnalyzer().getExpressionResult(expressionWithValue, req, SINGLETON_REP);
    }

    private String[] getExpression(String sessionID) {
        return SINGLETON_REP.getParametersData().get(sessionID).get(EXPRESSION).split(EMPTY_STRING);
    }

    private String getExpressionWithValue(String[] expression, String sessionID) {
        List<String> expressionList = new ArrayList<>(List.of(expression));
        IntStream.range(0, expressionList.size())
                .filter(i -> SINGLETON_REP.getParametersData().get(sessionID).containsKey(expressionList.get(i)))
                .forEach(i -> expressionList.set(i, SINGLETON_REP.getParametersData().get(sessionID).get(expressionList.get(i))));
        return String.join(EMPTY_STRING, expressionList);
    }
}