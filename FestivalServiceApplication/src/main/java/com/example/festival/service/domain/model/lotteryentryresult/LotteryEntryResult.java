package com.example.festival.service.domain.model.lotteryentryresult;

import com.example.festival.service.domain.Entity;
import com.example.festival.service.domain.model.entry.EntryId;
import com.example.festival.service.domain.model.festival.FestivalId;
import com.example.festival.service.domain.model.member.MemberId;

/**
 * 抽選結果.
 */
public class LotteryEntryResult implements Entity {

  FestivalId festivalId;

  MemberId memberId;

  EntryId entryId;

  LotteryResult lotteryResult;

  private LotteryEntryResult() {
  }

  /**
   * All argument constructor.
   */
  public LotteryEntryResult(
      FestivalId festivalId,
      MemberId memberId,
      EntryId entryId,
      LotteryResult lotteryResult) {

    this.festivalId = festivalId;
    this.memberId = memberId;
    this.entryId = entryId;
    this.lotteryResult = lotteryResult;
  }

  public LotteryResult lotteryResult() {
    return lotteryResult;
  }
}
