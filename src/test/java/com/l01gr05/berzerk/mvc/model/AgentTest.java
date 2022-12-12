package com.l01gr05.berzerk.mvc.model;

import com.l01gr05.berzerk.mvc.model.elements.Agent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgentTest {
    private Agent agent;

    @BeforeEach
    void setUp() {
        agent = new Agent(new Position(1, 1));
    }

    @Test
    public void testDefault() {
        Assertions.assertEquals('N', agent.getDirection());
    }

    @Test
    public void testChangePosition() {
        agent.setPosition(new Position(2, 2));
        Assertions.assertEquals(new Position(2, 2), agent.getPosition());
    }

    @Test
    public void testInitialPosition() {
        agent.setPosition(new Position(4, 5));
        Assertions.assertEquals(new Position(1, 1), agent.getInitialPosition());
    }

    @Test
    public void testDirection() {
        agent.setDirection('S');
        Assertions.assertEquals('S', agent.getDirection());
    }
}
