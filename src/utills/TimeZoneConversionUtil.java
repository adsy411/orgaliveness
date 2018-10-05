/**
 * Copyright (c) 2015, Sigma Aldrich. All rights reserved.
 *//*
package utills;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import javax.ws.rs.core.SecurityContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eaio.uuid.UUIDGen;
import com.sial.enotebook.core.entity.ActionMapping;
import com.sial.enotebook.core.entity.CommonUserInfo;
import com.sial.enotebook.core.entity.DateFields;
import com.sial.enotebook.user.json.LabJSON;
import com.sial.enotebook.user.json.UserJSON;

*//**
 * @author SRAMALIN
 * 23-Oct-2015
 * ENoteBook Biology
 *//*
public class TimeZoneConversionUtil<T> {
    private static final Logger LOGGER = LogManager.getLogger(TimeZoneConversionUtil.class.getName());
	private static final long NUM_100NS_INTERVALS_SINCE_UUID_EPOCH = 0x01b21dd213814000L;

    *//**
     * Generic function to set all lab dates to the entity passed.
     * @param entity T
     * @param actionValue String
     * @param labId UUID
     *//*
    public void convertDates(T entity, String actionValue, String timezone) {
        if (entity != null && timezone != null) {
            LOGGER.debug("Inside convertDates with timezone for entity " + entity.getClass().getName());
            Annotation annotation = entity.getClass().getAnnotation(ActionMapping.class);
            if (annotation != null) {
                LOGGER.debug("Found annotation");
                TimeZone tz = TimeZone.getTimeZone(timezone);
                LOGGER.debug("Lab timezone " + tz);
                if (tz == null) {
                    LOGGER.warn("Timezone information not Found !!!!");
                    return;
                }
                convertDatesWithTimezone(entity, actionValue, annotation, tz);
            }
        }
    }

    *//**
     * Convenient method to convert dates.
     * @param entity T
     * @param actionValue String
     * @param annotation Annotation
     * @param tz TimeZone
     *//*
    private void convertDatesWithTimezone(T entity, String actionValue, Annotation annotation, TimeZone tz) {
        LOGGER.debug("Converting date for action " + actionValue + " for class " + entity.getClass().getName()
                + " to timezone " + tz.getID());
        ActionMapping actionMap = (ActionMapping) annotation;
		for (DateFields dateFields : actionMap.dateFields()) {
			String action = dateFields.action();
			if (action.equals(actionValue)) {
				LOGGER.debug("Converting date for action " + action
						+ " for class " + entity.getClass().getName());
				for (String dateField : dateFields.dates()) {
					try {
						LOGGER.debug("About to convert the date for field "
								+ dateField);
						Object value = PropertyUtils.getProperty(entity,
								dateField);
						if (value != null
								&& value.getClass().getName()
										.equals(Date.class.getName())) {
							Date date = (Date) value;
							LOGGER.debug("Date for field " + dateField + " is "
									+ date);
							Date labDate = convertToLabTime(date, tz);
							PropertyUtils.setProperty(entity, dateField,
									labDate);
							LOGGER.debug("After Date for field " + dateField
									+ " is " + labDate);
						} else if (value != null
								&& value.getClass().getName()
										.equals(Long.class.getName())) {
							Long longValue = (Long) value;
							Date date = new Date(longValue);
							LOGGER.debug("Date for field " + dateField + " is "
									+ date);
							Date labDate = convertToLabTime(date, tz);
							PropertyUtils.setProperty(entity, dateField,
									labDate.getTime());
							LOGGER.debug("After Date for field " + dateField
									+ " is " + labDate.getTime());
						} else if (value != null
								&& value.getClass().getName()
										.equals(UUID.class.getName())) {
							UUID timeUUID = (UUID) value;
							Date date = new Date(getTimeFromUUID(timeUUID));
							LOGGER.debug("Date for field " + dateField + " is "
									+ date);
							Date labDate = convertToLabTime(date, tz);
							UUID cUUID = getTimeUUID(labDate.getTime());
							PropertyUtils.setProperty(entity, dateField, cUUID);
							LOGGER.debug("After Date for field " + dateField
									+ " is " + labDate.getTime());
						}

					} catch (IllegalAccessException e) {
						LOGGER.error("Error occurred while access the method "
								+ dateField, e);
					} catch (InvocationTargetException e) {
						LOGGER.error(
								"Error occurred while invoking the method "
										+ dateField, e);
					} catch (NoSuchMethodException e) {
						LOGGER.error("Error occurred in finding the method "
								+ dateField, e);
					}
				}
            }
        }
        LOGGER.debug("Entity Dates after conversion " + entity);
    }

    *//**
     * Converts date to lab timezone.
     * @param date Date
     * @param toTimeZone TimeZone
     * @return Date
     *//*
    public static Date convertToLabTime(Date date, TimeZone toTimeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (toTimeZone != null) {
            LOGGER.debug("convertToLabTime > Input Date : " + date);
            TimeZone fromTimeZone = calendar.getTimeZone();

            calendar.setTimeZone(fromTimeZone);
            if(!fromTimeZone.equals("CST")){
            calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
			if (fromTimeZone.inDaylightTime(calendar.getTime()))
				calendar.add(Calendar.MILLISECOND, calendar.getTimeZone()
						.getDSTSavings() * -1);
            }
            calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
            if(!toTimeZone.getID().equals("CST")){
			if (toTimeZone.inDaylightTime(calendar.getTime()))
				calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
        }
        }
        LOGGER.debug("convertToLabTime > output Date : " + calendar.getTime());
        return calendar.getTime();
    }

    *//**
	 * Converts UUID to lab timezone.
	 * 
	 * @param UUID
	 *            uuid
	 * @param toTimeZone
	 *            TimeZone
	 * @return UUID
	 *//*
	public static UUID convertToLabTime(UUID uuid, TimeZone toTimeZone) {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(getTimeFromUUID(uuid));
		calendar.setTime(date);
		if (toTimeZone != null) {
			LOGGER.debug("convertToLabTime UUID > Input UUID : " + uuid
					+ " Date : " + date);
			TimeZone fromTimeZone = calendar.getTimeZone();

			calendar.setTimeZone(fromTimeZone);
			 if(!fromTimeZone.equals("CST")){
			calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
			if (fromTimeZone.inDaylightTime(calendar.getTime())) {
				calendar.add(Calendar.MILLISECOND, calendar.getTimeZone()
						.getDSTSavings() * -1);
			}
			 }
			calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
			  if(!toTimeZone.getID().equals("CST")){
			if (toTimeZone.inDaylightTime(calendar.getTime())) {
				calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
			}
			  }
		}
		UUID cUUID = getTimeUUID(calendar.getTime().getTime());
		LOGGER.debug("convertToLabTime UUID > output UUID : " + cUUID
				+ " Date : " + calendar.getTime());
		return cUUID;
	}

	*//**
	 * Converts date to lab timezone.
	 * 
	 * @param date
	 *            Date
	 * @param toTimeZone
	 *            TimeZone
	 * @return Date
	 *//*
    public static Date convertToLabTime(Date date, String toTimeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (toTimeZone != null) {
			LOGGER.debug("Timezone : " + toTimeZone);
            TimeZone tz = TimeZone.getTimeZone(toTimeZone);
            LOGGER.debug("convertToLabTime > Input Date : " + date);
            TimeZone fromTimeZone = calendar.getTimeZone();

            calendar.setTimeZone(fromTimeZone);
            calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
            if(!fromTimeZone.equals("CST")){
			if (fromTimeZone.inDaylightTime(calendar.getTime()))
				calendar.add(Calendar.MILLISECOND, calendar.getTimeZone()
						.getDSTSavings() * -1);
            }
            calendar.add(Calendar.MILLISECOND, tz.getRawOffset());
            if(!tz.getID().equals("CST")){
			if (tz.inDaylightTime(calendar.getTime()))
				calendar.add(Calendar.MILLISECOND, tz.getDSTSavings());
        }
        }
        LOGGER.debug("convertToLabTime > output Date : " + calendar.getTime());
        return calendar.getTime();
    }

    *//**
     * Converts date to lab timezone.
     * @param date Date
     * @param securityContext SecurityContext
     * @return Date
     *//*
    public Date convertToLabTime(Date date, SecurityContext securityContext, String jSessionId) {
    	CommonUserInfo userInfo = UserInfoUtil.getUserInfoByUserId(securityContext,jSessionId);
        TimeZone tz = setTimeZone(userInfo.getLabId(),jSessionId);
        Calendar calendar = Calendar.getInstance();
        if (tz != null) {
            calendar.setTime(date);
            LOGGER.debug("convertToLabTime > Input Date : " + date);
            TimeZone fromTimeZone = calendar.getTimeZone();

            calendar.setTimeZone(fromTimeZone);
            calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
            if(!fromTimeZone.equals("CST")){
			if (fromTimeZone.inDaylightTime(calendar.getTime()))
				calendar.add(Calendar.MILLISECOND, calendar.getTimeZone()
						.getDSTSavings() * -1);
            }
            calendar.add(Calendar.MILLISECOND, tz.getRawOffset());
            if(!tz.getID().equals("CST")){
			if (tz.inDaylightTime(calendar.getTime()))
				calendar.add(Calendar.MILLISECOND, tz.getDSTSavings());
        }
        }

        LOGGER.debug("convertToLabTime > output Date : " + calendar.getTime());
        return calendar.getTime();
    }

    *//**
     * Sets the timezone from SecurityContext UserInfo
     * @param securityContext SecurityContext
     * @return TimeZone
     *//*
    private TimeZone setTimeZone(UUID labId, String jSessionId) {
        TimeZone tz = null;
        if (labId != null) {
            CommonUserInfo commonUserInfo = UserInfoUtil.getUserInfoByLabId(labId,jSessionId);
            // Get Lab timezone
            LabJSON lab = commonUserInfo.getLab();
            LOGGER.info("setTimezone for user " + commonUserInfo.getFirstName() + " : " + commonUserInfo.getLabId());
            if (lab != null && lab.getTimezone() != null) {
                String tokens[] = lab.getTimezone().split(" ");
                if (tokens != null && tokens.length == 2) {
                    tz = TimeZone.getTimeZone(tokens[1]);
                    LOGGER.debug("Lab Timezone : " + tz.getID());
                }
            }
        }
        return tz;
    }

    *//**
     * Fetches the timezone information from the security context object.
     * @param securityContext SecurityContext
     * @return Timezone ID in String
     *//*
    public static String getTimezone(UUID labId, String jSessionId) {
        String timezone = null;
        if (labId != null) {
            CommonUserInfo commonUserInfo = UserInfoUtil.getUserInfoByLabId(labId,jSessionId);
            // Get Lab timezone
			if (commonUserInfo != null) {
				LabJSON lab = commonUserInfo.getLab();
				LOGGER.info("setTimezone for user " + commonUserInfo.getFirstName()
						+ " : " + commonUserInfo.getLabId());
				if (lab != null && lab.getTimezone() != null) {
					String tokens[] = lab.getTimezone().split(" ");
					if (tokens != null && tokens.length == 2)
						timezone = tokens[1];
				}
			}
        }
        return timezone;
    }

	*//**
	 * The method getStringTimezone(UserJSON userJSONObject) accepts parameter
	 * as String
	 * 
	 * @param userJSONObject
	 * @return
	 *//*

	public static String getStringTimezone(UserJSON userJSONObject) {

		String timezone = null;
		// Get Lab timezone
		LabJSON lab = userJSONObject.getLab();

		if (lab != null && lab.getTimezone() != null) {
			String tokens[] = lab.getTimezone().split(" ");
			if (tokens != null && tokens.length == 2)
				timezone = tokens[1];
		}
		LOGGER.info("timezone>>>>>" + timezone);
		return timezone;
	}

	private static long getTimeFromUUID(UUID uuid) {
		return (uuid.timestamp() - NUM_100NS_INTERVALS_SINCE_UUID_EPOCH) / 10000;
	}

	private static java.util.UUID getTimeUUID(long time) {
		return new java.util.UUID(createTime(time),
				UUIDGen.getClockSeqAndNode());
	}

	private static long createTime(long currentTime) {
		long time;
		// UTC time
		long timeToUse = (currentTime * 10000)
				+ NUM_100NS_INTERVALS_SINCE_UUID_EPOCH;
		// time low
		time = timeToUse << 32;
		// time mid
		time |= (timeToUse & 0xFFFF00000000L) >> 16;
		// time hi and version
		time |= 0x1000 | ((timeToUse >> 48) & 0x0FFF); // version 1
		return time;
	}

}*/