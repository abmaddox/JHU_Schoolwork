import re
    
# Part 1: FASTA reading
# Written by Andrew Maddox

#this function finds all headers and retrieves
#the sequences associated with them in a FASTA file. A list containing
#the uppercase sequence is stored in a dictionary with each header
#as keys, then the dictionary is returned. This relies on
#the helper function __get_file_contents and __validate_file_contents.
#Returns a dictionary or None if the user quits. Ensures
#the selected file is in FASTA format.
def get_seq_dict()->dict:

    contents = __get_file_contents()
    seq_dict = dict()
    #Group 1 gives the header, group 2 gives the sequence in valid files
    pattern = r"(>\S+.*)\n([ATGCatgc\s]+)"
    
    #check to see if user wants to quit, indicated by empty string
    if contents == "":
        return seq_dict
    
    if __validate_file_contents(contents):
        #file is valid
        #this loop creates the dictionary with
        #header keys and values of a list with the sequence
        for m in re.finditer(pattern, contents):
            header = m.group(1)
            #Remove whitespace characters in sequences
            seq = re.sub(r"\s", "", m.group(2))
            seq = seq.upper()
            seq_dict.update({header:[seq]})
    else:
        #file is invalid and new file needs selection
        get_seq_dict()

    return seq_dict
            
def __validate_file_contents(contents : str)-> bool:
    #Search pattern to check for invalid characters, g1-header, g3-invalid chars
    validate_char_pattern = r"(>\S+.*)\n([ATGCatgc\s]*)([^ATGCatgc\s>]+)([ATGCatgc\s]*)"

    #Ensure the file has fasta headers, return None means it is invalid
    validate_headers_pattern = r"(>\S+.*)\n"

    #check to see if selected file is a FASTA file by format
    if re.search(validate_headers_pattern, contents) == None:
        print("ERROR: Selected file does not have FASTA formatted headers.\n")
        return False

    #check for invalid characters in the sequence of the file
    else:
        m = re.search(validate_char_pattern, contents)
        if(m is not None):
            #there are invalid chars in a sequence
            print("ERROR: Selected file contains invalid characters for a FASTA sequence.\n")
            return False
        else:  
            #reached if file contents are valid
            return True
    

#this function takes in the user input to validate that
#the file selected by the user exists, then returns the
#contents of the file. Returns an empty string if the user quits.
def __get_file_contents()->list:
    #sentinel variable used to control the cycle of user input and validation
    flag = 1
    while flag == 1:
        
        try:
            #try to read the entered file name and return the contents
            fname = input("Enter a FASTA file name: ")
            file = open(fname, 'r')
            contents = file.read()
            file.close()
            return contents
        
        except OSError:
            #notify user of error, ask them to proceed or quit, and validate their input
            flag = input("ERROR: File is unreadable or not found. Enter 1 to continue or 0 to quit.")
            while flag != '0' and flag != '1':
              flag = input("ERROR: Invalid input. Enter 1 to continue or 0 to quit.")
            if flag == '1':
                return __get_file_contents()
              
    #only reached in the case where a user wants to quit the program
    return ""


# Part 2: ORF isolation
# Written by Monique Nichols
# For use in ORF_Finder to produce reverse compliment of given sequence
def reverse_compliment(sequence):
    # Dictionary of base transations
    base_dict = {
        'A':'T',
        'T':'A',
        'C':'G',
        'G':'C'
    }
    
    counter = 0
    Translated = '' #empty string to add translated bases to
    while counter < len(sequence):
        Translated += base_dict[sequence[counter]] # translating sequence using dictionary base-by-base
        counter += 1
        
    ReverseCompliment = '' # empty string to append reversed bases to 
    for base in Translated:
        ReverseCompliment = base + ReverseCompliment # append each new base to the left of the previous - reversed!
        
    return ReverseCompliment



# For use in ORF_Finder, splits sequence into in-frame codons and checks to make sure none are premature STOP codons        
def STOP_Check(found_orf): 
    
    counter = 0
    while counter <len(found_orf)-3: # Don't want to include actual STOP codon in the True/False check below, so -3
        substr = found_orf[counter:counter+3] # splitting into codon and checking against STOPs
        
        if substr == 'TAA' or substr == 'TAG' or substr == 'TGA':
            return False # If False passed to ORF_Finder, ORF will not be included in final output
        counter += 3
        
    return True 


    
def ORF_Finder(FASTA_dict):
    valid = False
    min_length = ''
    while not valid:
        # try-except to make sure the user-entered minimum bp value is valid. Must be greater than 49 and an integer
        try:
            min_length = input('What is the desired minimum ORF length? Press Enter to default to 50bp: ')
            min_length = int(min_length)
            if min_length < 50:
                print('ERROR: Minimum value is 50, please try again.')
                valid = False
            else:
                valid = True
        
        # if input was not a number:
        except ValueError:
            if min_length == '': #Default = 50
                min_length = 50
                valid = True
            else:
                print('ERROR: Non-integer value entered, please try again.')
                valid = False
        
    

    return_list = [] # blank list that will be filled and returned
    
    for key in FASTA_dict:
        fwd_seq = FASTA_dict.get(str(key)) # forward sequence is the one in the input dictionary
        rev_seq = reverse_compliment(fwd_seq[0]) # reverse complement function above
        FASTA_dict[key].extend([rev_seq]) # add the reverse complement to the same key, so the header is still connected
        
        seq_type = 0 # bool to determine if we are working with the forward or reverse strand (1st vs 2nd sequence in list)
        for seq in FASTA_dict.get(key):
            seq_starts = [m.start(0) for m in re.finditer('ATG', seq)] # find all START match objects

            for start in seq_starts: # querying each start 
                sub_seq = seq[start:] # getting only sequence from start codon forward
                stop_codons = re.compile(('TAA|TAG|TGA')) #creating a regex search that accepts any of the three stop codons
                seq_ends = [m.start(0) for m in stop_codons.finditer(sub_seq)] # list of match object STOP positions

                for end in seq_ends: #for each STOP codon match, slice out string from the start codon to the stop
                    temp_ORF = sub_seq[:end+3]

                    if seq_type == 0: #Forward strand, frames 1-3 (see seq_type = 0 statement above)
                        # All found start-stop sequences screened against user input minimum ORF length,
                        # the STOP_Check function which detects premature stop codons within frame
                        # and the stop codon is ensured to be within frame of start codon
                        
                        if len(temp_ORF) % 3 == 0 and len(temp_ORF) >= min_length and STOP_Check(temp_ORF):
                            
                            if len(seq[:start]) % 3 == 0: #FRAME = 1    
                                # append list of valid ORF characteristics to return_list for output function
                                # (start + 1) accounts for Python counting from 0
                                return_list.append([key,temp_ORF,'FRAME = 1','POS = '+str(start+1),'LEN = '+str(len(temp_ORF))])
                            
                            elif len(seq[:start]) % 3 == 1: #FRAME = 2
                                return_list.append([key,temp_ORF,'FRAME = 2','POS = '+str(start+1),'LEN = '+str(len(temp_ORF))])
                                
                            elif len(seq[:start]) % 3 == 2: #FRAME = 3
                                return_list.append([key,temp_ORF,'FRAME = 3','POS = '+str(start+1),'LEN = '+str(len(temp_ORF))])

                    
                    # second sequence in FASTA_dict.get(key) is always the reverse strand sequence (seq_type != 0)

                    else: 
                        # if the seq is divisible by 3, FRAME = 1. (ATG start)
                        if len(temp_ORF) % 3 == 0 and len(temp_ORF) >= min_length and STOP_Check(temp_ORF):
                            
                            if len(seq[:start]) % 3 == 0: #FRAME = 4    
                                return_list.append([key,temp_ORF,'FRAME = 4','POS = '+str(0-start-1),'LEN = '+str(len(temp_ORF))])
                            
                            elif len(seq[:start]) % 3 == 1: #FRAME = 5
                                return_list.append([key,temp_ORF,'FRAME = 5','POS = '+str(0-start-1),'LEN = '+str(len(temp_ORF))])
                                
                            elif len(seq[:start]) % 3 == 2: #FRAME = 6
                                return_list.append([key,temp_ORF,'FRAME = 6','POS = '+str(0-start-1),'LEN = '+str(len(temp_ORF))])

            seq_type += 1    # making sure we fail the if statement above that writes positions for forward strand    

                    
    return return_list

# Part 3 : Formatting the output to a FASTA format
#Written by Mohamad Alayouni

# Edited by Andrew

# Defining the ‘print_orfs’ function which considers the three parameters header, orfs, and sequence
def print_orfs(nested_list : list):

    # Obtains a file name for the output file from the user
    valid = False
    output_file_name = ""
    while not valid:
        output_file_name = input("Please enter a file name for output: ")
        
        # Check for .txt extension, add it if not present in file name
        if output_file_name[len(output_file_name)-4:] != ".txt":
            output_file_name = output_file_name + ".txt"
        
        # Check if file already exists
        # Try block concludes if the file already exists
        try:
            f = open(output_file_name, 'r')
            f.close()
            valid = False
            print(f"ERROR: The file {output_file_name} already exists. Please try again.")

        
        # Reached if selected file name does not already exist
        except:
            valid = True
    
    
    # Open file for writing
    outfile = open(output_file_name, 'w', encoding = "UTF-8")

    
    # Extract info for each orf, format them, print them to the file 
    for orf in nested_list:
        accession = orf[0]
        orf_sequence = orf[1]
        frame = orf[2]
        pos = orf[3]
        length = orf[4]

        
        # Creates the final formatted header for an orf entry
        formatted_header = f"{accession} | {frame} {pos} {length}"


        # Uses regex to split sequence into a list of 3 char chunks
        codons = re.findall("...", orf_sequence)

        # Counts the number of codons in a line
        count = 0
        orf = ""
        # Print the codons to the output file, 15 codons per line
        for i in range(len(codons)):
            orf = orf + codons[i] + " "
            count += 1
            if count == 15:
                orf = orf.rstrip() + "\n"
                count = 0
        outfile.write(formatted_header + "\n" + orf.rstrip() +"\n")
        
    # Close file after all writing is completed
    outfile.close()

# Main function that ties all functions together for the gestalt function of isolating
# orfs from a file and printing them to another file.
def main():
    fasta_dict = get_seq_dict()
    orf_list = ORF_Finder(fasta_dict)
    print_orfs(orf_list)

if __name__ == "__main__":
    main()
