package app.glintcarwash.com.glintapp;

/**
 * Created by Harish on 01/01/16.
 */
public class CardSelector {

    public static final com.cooltechworks.creditcarddesign.CardSelector VISA = new com.cooltechworks.creditcarddesign.CardSelector(com.cooltechworks.creditcarddesign.R.drawable.card_color_round_rect_purple, com.cooltechworks.creditcarddesign.R.drawable.chip, com.cooltechworks.creditcarddesign.R.drawable.chip_inner, android.R.color.transparent, com.cooltechworks.creditcarddesign.R.drawable.ic_billing_visa_logo);
    public static final com.cooltechworks.creditcarddesign.CardSelector MASTER = new com.cooltechworks.creditcarddesign.CardSelector(com.cooltechworks.creditcarddesign.R.drawable.card_color_round_rect_pink, com.cooltechworks.creditcarddesign.R.drawable.chip_yellow, com.cooltechworks.creditcarddesign.R.drawable.chip_yellow_inner, android.R.color.transparent, com.cooltechworks.creditcarddesign.R.drawable.ic_billing_mastercard_logo);
    public static final com.cooltechworks.creditcarddesign.CardSelector AMEX = new com.cooltechworks.creditcarddesign.CardSelector(com.cooltechworks.creditcarddesign.R.drawable.card_color_round_rect_green, android.R.color.transparent, android.R.color.transparent, com.cooltechworks.creditcarddesign.R.drawable.img_amex_center_face, com.cooltechworks.creditcarddesign.R.drawable.ic_billing_amex_logo1);
    public static final com.cooltechworks.creditcarddesign.CardSelector DEFAULT = new com.cooltechworks.creditcarddesign.CardSelector(com.cooltechworks.creditcarddesign.R.drawable.card_color_round_rect_default, com.cooltechworks.creditcarddesign.R.drawable.chip, com.cooltechworks.creditcarddesign.R.drawable.chip_inner, android.R.color.transparent, android.R.color.transparent);
    private static final String AMEX_PREFIX = "3";


    private int mResCardId;
    private int mResChipOuterId;
    private int mResChipInnerId;
    private int mResCenterImageId;
    private int mResLogoId;

    public CardSelector(int mDrawableCard, int mDrawableChipOuter, int mDrawableChipInner, int mDrawableCenterImage, int logoId) {
        this.mResCardId = mDrawableCard;
        this.mResChipOuterId = mDrawableChipOuter;
        this.mResChipInnerId = mDrawableChipInner;
        this.mResCenterImageId = mDrawableCenterImage;
        this.mResLogoId = logoId;
    }


    public int getResCardId() {
        return mResCardId;
    }

    public void setResCardId(int mResCardId) {
        this.mResCardId = mResCardId;
    }

    public int getResChipOuterId() {
        return mResChipOuterId;
    }

    public void setResChipOuterId(int mResChipOuterId) {
        this.mResChipOuterId = mResChipOuterId;
    }

    public int getResChipInnerId() {
        return mResChipInnerId;
    }

    public void setResChipInnerId(int mResChipInnerId) {
        this.mResChipInnerId = mResChipInnerId;
    }

    public int getResCenterImageId() {
        return mResCenterImageId;
    }

    public void setResCenterImageId(int mResCenterImageId) {
        this.mResCenterImageId = mResCenterImageId;
    }

    public int getResLogoId() {
        return mResLogoId;
    }

    public void setResLogoId(int mResLogoId) {
        this.mResLogoId = mResLogoId;
    }


    public static com.cooltechworks.creditcarddesign.CardSelector selectCard(char cardFirstChar) {

        switch (cardFirstChar) {
            case '4':
                return VISA;
            case '5':
                return MASTER;
            case '3':
                return AMEX;
            default:
                return DEFAULT;
        }
    }

    public static com.cooltechworks.creditcarddesign.CardSelector selectCard(String cardNumber) {

        if(cardNumber != null && cardNumber.length() >= 3) {
            com.cooltechworks.creditcarddesign.CardSelector selector = selectCard(cardNumber.charAt(0));

            if(cardNumber.startsWith(AMEX_PREFIX)) {
                return AMEX;
            }

            if(selector != DEFAULT) {

                int[] drawables = { com.cooltechworks.creditcarddesign.R.drawable.card_color_round_rect_brown, com.cooltechworks.creditcarddesign.R.drawable.card_color_round_rect_green, com.cooltechworks.creditcarddesign.R.drawable.card_color_round_rect_pink, com.cooltechworks.creditcarddesign.R.drawable.card_color_round_rect_purple, com.cooltechworks.creditcarddesign.R.drawable.card_color_round_rect_blue};
                int hash = cardNumber.substring(0,3).hashCode();

                if(hash<0) {
                    hash = hash * -1;
                }

                int index = hash % drawables.length;

                int chipIndex = hash % 3;
                int[] chipOuter = { com.cooltechworks.creditcarddesign.R.drawable.chip, com.cooltechworks.creditcarddesign.R.drawable.chip_yellow, android.R.color.transparent};
                int[] chipInner = { com.cooltechworks.creditcarddesign.R.drawable.chip_inner, com.cooltechworks.creditcarddesign.R.drawable.chip_yellow_inner,android.R.color.transparent};


                selector.setResCardId(drawables[index]);
                selector.setResChipOuterId(chipOuter[chipIndex]);
                selector.setResChipInnerId(chipInner[chipIndex]);

                return selector;
            }
        }
        return DEFAULT;
    }
}
