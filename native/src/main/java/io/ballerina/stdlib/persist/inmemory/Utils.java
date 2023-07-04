package io.ballerina.stdlib.persist.inmemory;

import io.ballerina.runtime.api.PredefinedTypes;
import io.ballerina.runtime.api.creators.TypeCreator;
import io.ballerina.runtime.api.creators.ValueCreator;
import io.ballerina.runtime.api.types.RecordType;
import io.ballerina.runtime.api.utils.TypeUtils;
import io.ballerina.runtime.api.values.BArray;
import io.ballerina.runtime.api.values.BError;
import io.ballerina.runtime.api.values.BObject;
import io.ballerina.runtime.api.values.BStream;
import io.ballerina.runtime.api.values.BTypedesc;

import static io.ballerina.stdlib.persist.inmemory.Constants.PERSIST_IN_MEMORY_STREAM;
import static io.ballerina.stdlib.persist.inmemory.ModuleUtils.getModule;

/**
 * This class provides the SQL util methods for persistence.
 *
 * @since 1.1.0
 */
public class Utils {

    private static BObject createPersistInMemoryStream(BStream sqlStream, BTypedesc targetType, BArray fields,
                                                  BArray includes, BArray typeDescriptions, BObject persistClient,
                                                  BError persistError) {
        return ValueCreator.createObjectValue(getModule(), PERSIST_IN_MEMORY_STREAM,
                sqlStream, targetType, fields, includes, typeDescriptions, persistClient, persistError);
    }

    private static BStream createPersistInMemoryStreamValue(BTypedesc targetType, BObject persistSQLStream) {
        RecordType streamConstraint =
                (RecordType) TypeUtils.getReferredType(targetType.getDescribingType());
        return ValueCreator.createStreamValue(
                TypeCreator.createStreamType(streamConstraint, PredefinedTypes.TYPE_NULL), persistSQLStream);
    }

    public static BStream createPersistInMemoryStreamValue(BStream sqlStream, BTypedesc targetType, BArray fields,
                                                      BArray includes, BArray typeDescriptions, BObject persistClient,
                                                      BError persistError) {
        BObject persistSQLStream = createPersistInMemoryStream(sqlStream, targetType, fields, includes,
                typeDescriptions, persistClient, persistError);
        return createPersistInMemoryStreamValue(targetType, persistSQLStream);
    }
}