package com.hoho.instaliker.testcontainer;

import lombok.Builder;
import lombok.Data;
import org.testcontainers.lifecycle.TestDescription;

@Data
@Builder
public class TempDescription implements TestDescription {

     String testId;
     String filesystemFriendlyName;


}
