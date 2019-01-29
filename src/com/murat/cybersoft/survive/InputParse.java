package com.murat.cybersoft.survive;

import com.murat.cybersoft.survive.Models.Enemy;
import com.murat.cybersoft.survive.Models.Hero;
import com.murat.cybersoft.survive.Models.Resource;
import com.murat.cybersoft.survive.Models.Position;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParse {

    public Hero getHero(String input) {

        Hero hero = new Hero();
        hero.setHealthPower(getHealthPower("Hero", input));
        hero.setAttackPoints(getAttackPoints("Hero", input));

        return hero;
    }

    public List<Position> getRoute(String input) {

        List<Position> positionList = new ArrayList<Position>();
        Pattern routePat = Pattern.compile(Constants.positionRegex);
        Matcher routeMatcher = routePat.matcher(input);

        while (routeMatcher.find()) {
            String group = routeMatcher.group();

            Pattern enemyTypePattern = Pattern.compile("[a-z,A-Z]+");
            Matcher enemyTypeMatcher = enemyTypePattern.matcher(group);
            if (enemyTypeMatcher.find()) {
                String enemyType = enemyTypeMatcher.group();

                Pattern positionPattern = Pattern.compile("[0-9]+");
                Matcher positionMatcher = positionPattern.matcher(group);

                if (positionMatcher.find()) {
                    int position = Integer.valueOf(positionMatcher.group());
                    Position direction = new Position(getEnemy(enemyType, input), position);
                    positionList.add(direction);
                }
            }
        }

        Resource resource = getResource(input);
        Iterator<Position> iterator = positionList.iterator();
        while (iterator.hasNext()) {
            Position position = iterator.next();
            if (position.getPosition() > resource.getDistance()) {
                iterator.remove();
            }
        }

        if (!positionList.isEmpty()) {
            positionList.sort(new PositionCompare());
        }

        return positionList;
    }

    private Resource getResource(String input) {
        Pattern resourcesPat = Pattern.compile(Constants.resourcesRegex);
        Matcher resourceMatcher = resourcesPat.matcher(input);

        while (resourceMatcher.find()) {
            String resourceMatch = resourceMatcher.group();
            Pattern metersPattern = Pattern.compile("[0-9]+");
            Matcher metersMatchMatcher = metersPattern.matcher(resourceMatch);
            if (metersMatchMatcher.find()) {
                int metersMatch = Integer.valueOf(metersMatchMatcher.group());
                return new Resource(metersMatch);
            }
        }
        return new Resource(0);
    }

    private Enemy getEnemy(String type, String input) {

        Pattern pattern = Pattern.compile(Constants.enemyRegex);
        Matcher matcher = pattern.matcher(input);

        Enemy enemy = null;
        while (matcher.find()) {

            String match = matcher.group();
            Pattern typePattern = Pattern.compile(type + " is Enemy");
            Matcher typeMatcher = typePattern.matcher(match);
            if (typeMatcher.find()) {
                enemy = new Enemy(type);
                enemy.setHealthPower(getHealthPower(type, input));
                enemy.setAttackPoints(getAttackPoints(type, input));
            }
        }

        return enemy;

    }

    private int getAttackPoints(String type, String input) {
        Pattern apPattern = Pattern.compile(type + " " + Constants.attackPointsRegex);
        return make(apPattern,input);

    }

    private int getHealthPower(String type, String input) {
        Pattern hpPattern = Pattern.compile(type + " " + Constants.healthPowerRegex);
        return make(hpPattern,input);
    }

    private int make(Pattern pattern,String input) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String match = input.substring(matcher.start(), matcher.end());
            Pattern numeric = Pattern.compile("[0-9]+");
            Matcher numericMatcher = numeric.matcher(match);
            if (numericMatcher.find()) {
                int hp = Integer.valueOf(match.substring(numericMatcher.start(), numericMatcher.end()));
                return hp;
            }
        }
        return 0;
    }
}
