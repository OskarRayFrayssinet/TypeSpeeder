/*
 * Class: StatusService
 * Description: A support class for the class Status.
 * Author: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-18
 */
package se.ju23.typespeeder.service;

import org.springframework.stereotype.Service;
import se.ju23.typespeeder.enums.Status;

@Service
public class StatusService {

    private Status status;

    public Status getStatus() {
        return status;
    }

    public Status setStatus(Status status) {
        this.status = status;
        return status;
    }

}
