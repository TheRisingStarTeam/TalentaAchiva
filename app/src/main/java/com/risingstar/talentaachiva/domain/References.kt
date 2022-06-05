package com.risingstar.talentaachiva.domain

/**
 * FOR THE SAKE OF OUR SANITY, PLEASE USE THIS INSTEAD OF LITERAL STRING!!!
 */
object References {

    //EVENT
    const val EVENT = "events"
    const val EVENT_ID = "eventId"
    const val EVENT_BANNER = "banner"
    const val EVENT_STATUS = "active"
    const val EVENT_NAME = "name"
    const val EVENT_CATEGORY = "categories"
    const val EVENT_TAGS = "hashtags"
    const val EVENT_RULES = "rules"
    const val EVENT_DESCRIPTION = "description"
    const val EVENT_PARTICIPANT = "participants"
    const val EVENT_ORGANIZER = "organizers"
    const val EVENT_TOS = "tos"
    const val EVENT_POST ="postsId"
    const val EVENT_ASSIGNMENT = "assignments"
    const val EVENT_CREATION = "creationDate"
    const val EVENT_END = "endDate"

    //SUBMISSION
    const val SUBMISSION = "submissions"
    const val SUBMISSION_ID = "submissionId"
    const val SUBMISSION_ASSIGNMENT = "assignmentId"
    const val SUBMISSION_TITLE = "title"
    const val SUBMISSION_CREATION_DATE = "dateOfSubmission"
    const val SUBMISSION_TYPE = "type"
    const val SUBMISSION_CONTENT = "content"
    const val SUBMISSION_DESCRIPTION = "description"
    const val SUBMISSION_AUTHOR = "author"
    const val SUBMISSION_SCORE = "score"

    //ASSIGNMENT
    const val ASSIGNMENT = "assignments"
    const val ASSIGNMENT_ID = "assignmentId"
    const val ASSIGNMENT_TITLE = "title"
    const val ASSIGNMENT_RULE = "rules"
    const val ASSIGNMENT_DESCRIPTION = "description"
    const val ASSIGNMENT_EVENT = "eventId"
    const val ASSIGNMENT_CRITERIA = "criteria"

    //IDENTITY
    const val USER = "userIdentities"
    const val USER_ID = "userId"
    const val USER_NAME = "name"
    const val USER_BIRTHDAY = "dateOfBirth"
    const val USER_PHONE = "phoneNumber"
    const val USER_ADDRESS = "address"
    const val USER_INTEREST = "interest"
    const val USER_SOSMED = "socialMedia"
    const val USER_HISTORY = "history"

    //POSTS
    const val POST = "posts"
    const val POST_ID = "postId"
    const val POST_EVENT = "eventId"
    const val POST_TITLE = "title"
    const val POST_CONTENT = "content"
    const val POST_RECIPIENTS = "recipients"
}