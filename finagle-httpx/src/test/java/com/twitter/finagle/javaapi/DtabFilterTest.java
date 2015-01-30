package com.twitter.finagle.javaapi;

import com.twitter.finagle.httpx.Ask;
import com.twitter.finagle.httpx.Response;
import com.twitter.finagle.httpx.filter.DtabFilter;

/**
 * A Java compilation test for DtabFilter.
 */

public class DtabFilterTest {
  static {
    DtabFilter<Ask, Response> finagleDtabFilter = new DtabFilter.Finagle<Ask>();
  }
}
