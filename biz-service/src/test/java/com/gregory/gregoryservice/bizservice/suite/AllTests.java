package com.gregory.gregoryservice.bizservice.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({
    "com.gregory.gregoryservice.bizservice.unit",
    "com.gregory.gregoryservice.bizservice.integration"
})
public class AllTests {

}
