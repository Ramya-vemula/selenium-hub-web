package com.tissl.utils;

import com.tissl.TestSetup;

public class TestData {

    private static TestSetup setup;

    static {
        setup = new TestSetup();
    }

    public static String getProperty(final String propertyKey) {
        return setup.getProperties().getProperty(propertyKey);
    }

    public static final String USER_NAME = getProperty("USER_NAME");

    public static final String USER_PASSWORD = getProperty("USER_PASSWORD");

    public static String EXPIRED_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX3N0YXR1cyI6IkFDVElWRSIsInVzZXJfaWQiOiIwb2dseHViSERxNUVnYjJJZHgrcXBFaUxDck5DRDZoUHJoMDdIQUFob1NvNE1iUlIrUEVPbWdzSmJBWFZvREVWIiwidXNlcl9uYW1lIjoiamFtZXMuZ3JpZmZpbit3ZWJAcnVuZ3dheS5jb20iLCJwcm9maWxlX2lkIjoiOTY4ODdmZGQtNjBiNy00NDk4LWI0N2ItYmEzOWUwMDQyYmI1Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sInJvbGVzIjpbIlVTRVIiXSwiZXhwIjoxNTE5OTQ0MDg4LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiNTI3ZDUxMzgtMWQzNi00Zjk0LWJmMzctOGVkYmExMGMyNmQ4IiwiY2xpZW50X2lkIjoicnVuZ3dheS1jbGllbnQifQ.bQmMUYwYB5Guqjr0H4YTrqmMHt1ZVMI8kENbieJK7OVrZ5oXSRDaaDKFJDJyT9Hkhj8irkGM0oW53R69feFnVopfRXe91avmT0sFdWGd7TV64kc43KjZ6eTbOGKIlAbyITdChbiOeZkVWOI29IErEdZqkjhZWsytWUSURNAIDGI9A1StiDvmYKzdPN-XSGG60AaA8PZaWjOMF5dXdSAizDHwwxFOYhZXYh-GF1Uj-NhDM-ybwfAA-pzluvzb5UICj4X9l0azne3vgAl4BgKa0dWlyoU-nMtHHHBZVrNvuiZwIGTByLxYneuNcfSPOMMdFbrUj0S5D9XypQu70F8gGw";


}
