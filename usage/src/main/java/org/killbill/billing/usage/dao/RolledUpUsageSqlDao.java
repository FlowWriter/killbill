/*
 * Copyright 2010-2013 Ning, Inc.
 * Copyright 2014-2019 Groupon, Inc
 * Copyright 2014-2019 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.usage.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.killbill.billing.callcontext.InternalTenantContext;
import org.killbill.billing.util.entity.Entity;
import org.killbill.billing.util.entity.dao.EntitySqlDao;
import org.killbill.commons.jdbi.binder.SmartBindBean;
import org.killbill.commons.jdbi.template.KillBillSqlDaoStringTemplate;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

@KillBillSqlDaoStringTemplate
public interface RolledUpUsageSqlDao extends EntitySqlDao<RolledUpUsageModelDao, Entity> {

    @SqlQuery
    Long recordsWithTrackingIdExist(@Bind("subscriptionId") final UUID subscriptionId,
                                    @Bind("trackingId") final String trackingId,
                                    @SmartBindBean final InternalTenantContext context);

    @SqlQuery
    List<RolledUpUsageModelDao> getUsageForSubscription(@Bind("subscriptionId") final UUID subscriptionId,
                                                        @Bind("startDate") final Date startDate,
                                                        @Bind("endDate") final Date endDate,
                                                        @Bind("unitType") final String unitType,
                                                        @SmartBindBean final InternalTenantContext context);

    @SqlQuery
    List<RolledUpUsageModelDao> getAllUsageForSubscription(@Bind("subscriptionId") final UUID subscriptionId,
                                                           @Bind("startDate") final Date startDate,
                                                           @Bind("endDate") final Date endDate,
                                                           @SmartBindBean final InternalTenantContext context);

    @SqlQuery
    List<RolledUpUsageModelDao> getRawUsageForAccount(@Bind("startDate") final Date startDate,
                                                      @Bind("endDate") final Date endDate,
                                                      @SmartBindBean final InternalTenantContext context);
}
