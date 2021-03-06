/**
 *
 *    Copyright 2017-2018, Optimizely and contributors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.optimizely.ab.notification;

import com.optimizely.ab.config.Experiment;
import com.optimizely.ab.config.Variation;
import com.optimizely.ab.event.LogEvent;

import javax.annotation.Nonnull;
import java.util.Map;


public abstract class ActivateNotificationListener implements NotificationListener, ActivateNotificationListenerInterface {

    /**
     * Base notify called with var args.  This method parses the parameters and calls the abstract method.
     * @param args - variable argument list based on the type of notification.
     */
    @Override
    public final void notify(Object... args) {
        assert(args[0] instanceof Experiment);
        Experiment experiment = (Experiment) args[0];
        assert(args[1] instanceof String);
        String userId = (String) args[1];
        Map<String, ?> attributes = null;
        if (args[2] != null) {
            assert (args[2] instanceof java.util.Map);
            attributes = (Map<String, ?>) args[2];
        }
        assert(args[3] instanceof Variation);
        Variation variation = (Variation) args[3];
        assert(args[4] instanceof LogEvent);
        LogEvent logEvent = (LogEvent) args[4];

        onActivate(experiment, userId, attributes, variation, logEvent);
    }

    /**
     * onActivate called when an activate was triggered
     * @param experiment - The experiment object being activated.
     * @param userId - The userId passed into activate.
     * @param attributes - The filtered attribute list passed into activate
     * @param variation - The variation that was returned from activate.
     * @param event - The impression event that was triggered.
     */
    public abstract void onActivate(@Nonnull Experiment experiment,
                             @Nonnull String userId,
                             @Nonnull Map<String, ?> attributes,
                             @Nonnull Variation variation,
                             @Nonnull LogEvent event) ;

}

