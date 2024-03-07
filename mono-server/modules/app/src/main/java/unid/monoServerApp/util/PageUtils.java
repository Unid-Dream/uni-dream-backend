package unid.monoServerApp.util;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import unid.monoServerMeta.api.UniPageResponse;

import java.util.List;
import java.util.UUID;

public class PageUtils {

    public static <T> UniPageResponse<T> fromResult(DSLContext dslContext, SelectLimitStep<Record> query, int currentPage, int pageSize, Class<T> type) {
        // 计算总记录数
        int totalRecords = dslContext.fetchCount(query);

        // 计算总页数
        int totalPages = (totalRecords + pageSize - 1) / pageSize;

        // 应用分页参数
        List<T> records = query
                .limit(pageSize)
                .offset((currentPage - 1) * pageSize)
                .fetchInto(type);

        // 创建并返回分页结果
        return new UniPageResponse<>(totalRecords,currentPage,pageSize,totalPages,records);
    }
}
