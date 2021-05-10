package com.example.festival.service.domain.model.member;

import com.example.festival.service.domain.ValueObject;

import java.util.Objects;

/**
 * 会員番号.
 */
public class MemberId implements ValueObject {

  private Integer value;

  private MemberId() {
  }

  public MemberId(Integer value) {
    this.value = value;
  }

  public Integer value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MemberId memberId = (MemberId) o;
    return Objects.equals(value, memberId.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
