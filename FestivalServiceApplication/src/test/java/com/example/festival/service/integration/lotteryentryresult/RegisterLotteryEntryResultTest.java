package com.example.festival.service.integration.lotteryentryresult;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.db.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.festival.service.application.lotteryentryresult.RegisterLotteryEntryResultRequest;
import com.example.festival.service.domain.model.lotteryentryresult.LotteryResult;
import com.example.festival.service.integration.TestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.operation.Operation;
import java.time.LocalDate;
import javax.sql.DataSource;

import org.assertj.db.type.Changes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RegisterLotteryEntryResultTest {

  private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void prepare() {

    final Operation insertFestivals =
        insertInto("festivals")
            .columns("festival_id", "festival_name", "sponsor_id",
                "held_date", "held_place_prefecture_code")
            .values(1, "??????????????????????????????", 1, LocalDate.of(2019, 10, 1), 13)
            .values(2, "??????????????????????????????", 1, LocalDate.of(2019, 11, 1), 13)
            .build();

    final Operation insertEntries =
        insertInto("entries")
            .row()
            .column("festival_id", 1)
            .column("entry_id", 1)
            .column("entry_name", "?????????")
            .column("entry_description", "???????????????")
            .column("event_code", 1)
            .column("capacity", 5)
            .column("participation_fees", 1000)
            .column("application_numbers", 5)
            .column("first_arrival_lottery_type", "firstArrival")
            .column("application_start_date", LocalDate.of(2019, 9, 11))
            .column("application_end_date", LocalDate.of(2019, 9, 20))
            .column("entry_status", "participantConfirmation")
            .end()
            .row()
            .column("festival_id", 1)
            .column("entry_id", 2)
            .column("entry_name", "??????????????????")
            .column("entry_description", "??????????????????")
            .column("event_code", 1)
            .column("capacity", 5)
            .column("participation_fees", 500)
            .column("application_numbers", 10)
            .column("first_arrival_lottery_type", "lottery")
            .column("application_start_date", LocalDate.of(2019, 9, 21))
            .column("application_end_date", LocalDate.of(2019, 9, 25))
            .column("entry_status", "underLottery")
            .end()
            .row()
            .column("festival_id", 1)
            .column("entry_id", 3)
            .column("entry_name", "??????????????????")
            .column("entry_description", "????????????????????????")
            .column("event_code", 1)
            .column("capacity", 5)
            .column("participation_fees", 1000)
            .column("application_numbers", 10)
            .column("first_arrival_lottery_type", "lottery")
            .column("application_start_date", LocalDate.of(2018, 9, 26))
            .column("application_end_date", LocalDate.of(2018, 9, 27))
            .column("entry_status", "underLottery")
            .end()
            .row()
            .column("festival_id", 1)
            .column("entry_id", 4)
            .column("entry_name", "??????????????????")
            .column("entry_description", "????????????????????????")
            .column("event_code", 1)
            .column("capacity", 5)
            .column("participation_fees", 1000)
            .column("application_numbers", 10)
            .column("first_arrival_lottery_type", "lottery")
            .column("application_start_date", LocalDate.of(2018, 9, 28))
            .column("application_end_date", LocalDate.of(2018, 9, 29))
            .column("entry_status", "beforeRecruitment")
            .end()
            .row()
            .column("festival_id", 2)
            .column("entry_id", 5)
            .column("entry_name", "??????????????????")
            .column("entry_description", "????????????????????????")
            .column("event_code", 1)
            .column("capacity", 5)
            .column("participation_fees", 1000)
            .column("application_numbers", 0)
            .column("first_arrival_lottery_type", "lottery")
            .column("application_start_date", LocalDate.of(2018, 10, 21))
            .column("application_end_date", LocalDate.of(2018, 10, 25))
            .column("entry_status", "underLottery")
            .end()
            .row()
            .column("festival_id", 1)
            .column("entry_id", 6)
            .column("entry_name", "??????????????????")
            .column("entry_description", "????????????????????????")
            .column("event_code", 1)
            .column("capacity", 5)
            .column("participation_fees", 1000)
            .column("application_numbers", 10)
            .column("first_arrival_lottery_type", "lottery")
            .column("application_start_date", LocalDate.of(2018, 9, 28))
            .column("application_end_date", LocalDate.of(2018, 9, 29))
            .column("entry_status", "underLottery")
            .end()
            .build();

    final Operation insertLotteryEntries =
        insertInto("lottery_entries")
            .columns("festival_id", "entry_id", "lottery_date", "following_entry_id")
            .values(1, 2, LocalDate.of(2019, 9, 26), 3)
            .values(1, 3, LocalDate.of(2018, 9, 28), null)
            .build();

    final Operation insertMembers =
        insertInto("members")
            .columns("member_id", "member_name", "sex", "birthday",
                "address", "phone_number", "email")
            .values(1, "?????? ??????", "man", LocalDate.of(1990, 4, 1),
                "???????????????????????????", "090-1111-1111", "ezaki@example.com")
            .values(2, "????????? ??????", "man", LocalDate.of(1990, 5, 1),
                "????????????????????????", "090-2222-2222", "tsukui@example.com")
            .values(3, "?????? ?????????", "woman", LocalDate.of(1990, 6, 1),
                "???????????????????????????", "090-3333-3333", "horiuchi@example.com")
            .build();

    final Operation insertApplications =
        insertInto("applications")
            .row()
            .column("festival_id", 1)
            .column("member_id", 1)
            .column("entry_id", 2)
            .column("application_date", LocalDate.of(2019, 9, 21))
            .column("payment_date", null)
            .column("use_points", 0)
            .end()
            .row()
            .column("festival_id", 1)
            .column("member_id", 2)
            .column("entry_id", 2)
            .column("application_date", LocalDate.of(2019, 9, 21))
            .column("payment_date", null)
            .column("use_points", 0)
            .end()
            .row()
            .column("festival_id", 1)
            .column("member_id", 3)
            .column("entry_id", 2)
            .column("application_date", LocalDate.of(2019, 9, 21))
            .column("payment_date", null)
            .column("use_points", 0)
            .end()
            .build();

    final Operation insertLotteryEntryResults =
        insertInto("lottery_entry_results")
            .row()
            .column("festival_id", 1)
            .column("member_id", 2)
            .column("entry_id", 2)
            .column("lottery_result", "failed")
            .end()
            .row()
            .column("festival_id", 1)
            .column("member_id", 3)
            .column("entry_id", 2)
            .column("lottery_result", "winning")
            .end()
            .build();

    Operation operation = sequenceOf(
        TestHelper.deleteAll,
        TestHelper.resetSequences,
        TestHelper.insertSponsors,
        TestHelper.insertEventTypes,
        TestHelper.insertEvents,
        insertFestivals,
        insertEntries,
        insertLotteryEntries,
        insertMembers,
        insertApplications,
        insertLotteryEntryResults);

    Destination dest = new DataSourceDestination(dataSource);
    DbSetup dbSetup = new DbSetup(dest, operation);
    dbSetupTracker.launchIfNecessary(dbSetup);
  }

  @DisplayName("??????????????????????????????????????????????????????????????????????????????")
  @Test
  void test1() throws Exception {

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();
    request.setFestivalId(1);
    request.setMemberId(1);
    request.setEntryId(2);
    request.setLotteryResult(LotteryResult.winning);

    final String requestJson = objectMapper.writeValueAsString(request);

    Changes changes = new Changes(dataSource);
    changes.setStartPointNow();

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isOk());

    changes.setEndPointNow();

    assertThat(changes)
        .hasNumberOfChanges(1)
        .changeOnTable("lottery_entry_results")
        .isCreation()
        .rowAtEndPoint()
        .value("festival_id").isEqualTo(1)
        .value("member_id").isEqualTo(1)
        .value("entry_id").isEqualTo(2)
        .value("lottery_result").isEqualTo("winning");
  }

  @DisplayName("??????????????????????????????????????????????????????")
  @Test
  void test2() throws Exception {

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();
    request.setFestivalId(1);
    request.setMemberId(2);
    request.setEntryId(3);
    request.setLotteryResult(LotteryResult.failed);

    final String requestJson = objectMapper.writeValueAsString(request);

    Changes changes = new Changes(dataSource);
    changes.setStartPointNow();

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isOk());

    changes.setEndPointNow();

    assertThat(changes)
        .hasNumberOfChanges(1)
        .changeOnTable("lottery_entry_results")
        .isCreation()
        .rowAtEndPoint()
        .value("festival_id").isEqualTo(1)
        .value("member_id").isEqualTo(2)
        .value("entry_id").isEqualTo(3)
        .value("lottery_result").isEqualTo("failed");
  }

  @DisplayName("????????????????????????null????????????????????????????????????")
  @Test
  void testValidationError() throws Exception {

    dbSetupTracker.skipNextLaunch();

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();

    final String requestJson = objectMapper.writeValueAsString(request);

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("???????????????????????????????????????")));
  }

  @DisplayName("??????????????????????????????????????????????????????????????????")
  @Test
  void testBusinessError1() throws Exception {

    dbSetupTracker.skipNextLaunch();

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();
    request.setFestivalId(1);
    request.setMemberId(1);
    request.setEntryId(5);
    request.setLotteryResult(LotteryResult.winning);

    final String requestJson = objectMapper.writeValueAsString(request);

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("????????????????????????????????????")));
  }

  @DisplayName("???????????????????????????????????????????????????????????????")
  @Test
  void testBusinessError2() throws Exception {

    dbSetupTracker.skipNextLaunch();

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();
    request.setFestivalId(1);
    request.setMemberId(1);
    request.setEntryId(1);
    request.setLotteryResult(LotteryResult.winning);

    final String requestJson = objectMapper.writeValueAsString(request);

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("?????????????????????????????????????????????")));
  }

  @DisplayName("???????????????????????????????????????????????????????????????")
  @Test
  void testBusinessError3() throws Exception {

    dbSetupTracker.skipNextLaunch();

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();
    request.setFestivalId(1);
    request.setMemberId(1);
    request.setEntryId(4);
    request.setLotteryResult(LotteryResult.winning);

    final String requestJson = objectMapper.writeValueAsString(request);

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("???????????????????????????????????????")));
  }

  @DisplayName("?????????????????????????????????????????????????????????")
  @Test
  void testBusinessError4() throws Exception {

    dbSetupTracker.skipNextLaunch();

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();
    request.setFestivalId(1);
    request.setMemberId(4);
    request.setEntryId(2);
    request.setLotteryResult(LotteryResult.winning);

    final String requestJson = objectMapper.writeValueAsString(request);

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("???????????????????????????")));
  }

  @DisplayName("????????????????????????????????????????????????????????????????????????")
  @Test
  void testBusinessError5() throws Exception {

    dbSetupTracker.skipNextLaunch();

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();
    request.setFestivalId(1);
    request.setMemberId(2);
    request.setEntryId(2);
    request.setLotteryResult(LotteryResult.winning);

    final String requestJson = objectMapper.writeValueAsString(request);

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("???????????????????????????????????????")));
  }

  @DisplayName("????????????????????????????????????????????????????????????")
  @Test
  void testBusinessError6() throws Exception {

    dbSetupTracker.skipNextLaunch();

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();
    request.setFestivalId(2);
    request.setMemberId(1);
    request.setEntryId(5);
    request.setLotteryResult(LotteryResult.winning);

    final String requestJson = objectMapper.writeValueAsString(request);

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("???????????????????????????????????????????????????")));
  }

  @DisplayName("??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????")
  @Test
  void testBusinessError7() throws Exception {

    dbSetupTracker.skipNextLaunch();

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();
    request.setFestivalId(1);
    request.setMemberId(1);
    request.setEntryId(6);
    request.setLotteryResult(LotteryResult.winning);

    final String requestJson = objectMapper.writeValueAsString(request);

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("???????????????????????????????????????")));
  }

  @DisplayName("????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????")
  @Test
  void testBusinessError8() throws Exception {

    dbSetupTracker.skipNextLaunch();

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();
    request.setFestivalId(1);
    request.setMemberId(1);
    request.setEntryId(3);
    request.setLotteryResult(LotteryResult.winning);

    final String requestJson = objectMapper.writeValueAsString(request);

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("?????????????????????????????????????????????????????????????????????????????????")));
  }

  @DisplayName("?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????")
  @Test
  void testBusinessError9() throws Exception {

    dbSetupTracker.skipNextLaunch();

    RegisterLotteryEntryResultRequest request = new RegisterLotteryEntryResultRequest();
    request.setFestivalId(1);
    request.setMemberId(3);
    request.setEntryId(3);
    request.setLotteryResult(LotteryResult.winning);

    final String requestJson = objectMapper.writeValueAsString(request);

    mockMvc.perform(MockMvcRequestBuilders.post("/lottery-entry-result")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(requestJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("??????????????????????????????????????????????????????????????????????????????????????????")));
  }
}
