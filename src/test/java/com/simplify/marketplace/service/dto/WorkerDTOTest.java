package com.simplify.marketplace.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.simplify.marketplace.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WorkerDTO.class);
        WorkerDTO workerDTO1 = new WorkerDTO();
        workerDTO1.setId("id1");
        WorkerDTO workerDTO2 = new WorkerDTO();
        assertThat(workerDTO1).isNotEqualTo(workerDTO2);
        workerDTO2.setId(workerDTO1.getId());
        assertThat(workerDTO1).isEqualTo(workerDTO2);
        workerDTO2.setId("id2");
        assertThat(workerDTO1).isNotEqualTo(workerDTO2);
        workerDTO1.setId(null);
        assertThat(workerDTO1).isNotEqualTo(workerDTO2);
    }
}
