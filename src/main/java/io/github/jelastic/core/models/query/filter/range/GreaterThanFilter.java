/*
 * Copyright 2019 Koushik R <rkoushik.14@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.jelastic.core.models.query.filter.range;

import io.github.jelastic.core.models.query.filter.FilterType;
import io.github.jelastic.core.models.query.filter.FilterVisitor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by koushikr
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GreaterThanFilter extends RangeFilter {

    public GreaterThanFilter() {
        super(FilterType.GREATER_THAN);
    }

    public GreaterThanFilter(String field, Object value, boolean includeLower, boolean includeUpper) {
        super(FilterType.GREATER_THAN, field, value, includeLower, includeUpper);
    }

    @Override
    public <V> V accept(FilterVisitor<V> visitor) {
        return visitor.visit(this);
    }

}
